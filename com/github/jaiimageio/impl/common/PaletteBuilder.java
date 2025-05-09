package com.github.jaiimageio.impl.common;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import javax.imageio.ImageTypeSpecifier;

/* loaded from: classes3.dex */
public class PaletteBuilder {
    protected static final int MAXLEVEL = 8;
    protected int currLevel;
    protected int currSize;
    protected int maxNodes;
    protected int numNodes;
    protected ColorNode[] palette;
    protected ColorNode[] reduceList;
    protected int requiredSize;
    protected ColorNode root;
    protected RenderedImage src;
    protected ColorModel srcColorModel;
    protected Raster srcRaster;
    protected ColorNode transColor;
    protected int transparency;

    public static RenderedImage createIndexedImage(RenderedImage renderedImage) {
        PaletteBuilder paletteBuilder = new PaletteBuilder(renderedImage);
        paletteBuilder.buildPalette();
        return paletteBuilder.getIndexedImage();
    }

    public static IndexColorModel createIndexColorModel(RenderedImage renderedImage) {
        PaletteBuilder paletteBuilder = new PaletteBuilder(renderedImage);
        paletteBuilder.buildPalette();
        return paletteBuilder.getIndexColorModel();
    }

    public static boolean canCreatePalette(ImageTypeSpecifier imageTypeSpecifier) {
        if (imageTypeSpecifier != null) {
            return true;
        }
        throw new IllegalArgumentException("type == null");
    }

    public static boolean canCreatePalette(RenderedImage renderedImage) {
        if (renderedImage == null) {
            throw new IllegalArgumentException("image == null");
        }
        return canCreatePalette(new ImageTypeSpecifier(renderedImage));
    }

    protected RenderedImage getIndexedImage() {
        BufferedImage bufferedImage = new BufferedImage(this.src.getWidth(), this.src.getHeight(), 13, getIndexColorModel());
        WritableRaster raster = bufferedImage.getRaster();
        int minX = this.src.getMinX();
        int minY = this.src.getMinY();
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int i2 = 0; i2 < bufferedImage.getWidth(); i2++) {
                raster.setSample(i2, i, 0, findColorIndex(this.root, getSrcColor(i2 + minX, i + minY)));
            }
        }
        return bufferedImage;
    }

    protected PaletteBuilder(RenderedImage renderedImage) {
        this(renderedImage, 256);
    }

    protected PaletteBuilder(RenderedImage renderedImage, int i) {
        this.src = renderedImage;
        this.srcColorModel = renderedImage.getColorModel();
        this.srcRaster = renderedImage.getData();
        int transparency = this.srcColorModel.getTransparency();
        this.transparency = transparency;
        if (transparency != 1) {
            this.requiredSize = i - 1;
            ColorNode colorNode = new ColorNode();
            this.transColor = colorNode;
            colorNode.isLeaf = true;
            return;
        }
        this.requiredSize = i;
    }

    private Color getSrcColor(int i, int i2) {
        return new Color(this.srcColorModel.getRGB(this.srcRaster.getDataElements(i, i2, (Object) null)), this.transparency != 1);
    }

    protected int findColorIndex(ColorNode colorNode, Color color) {
        if (this.transparency != 1 && color.getAlpha() != 255) {
            return 0;
        }
        if (colorNode.isLeaf) {
            return colorNode.paletteIndex;
        }
        return findColorIndex(colorNode.children[getBranchIndex(color, colorNode.level)], color);
    }

    protected void buildPalette() {
        this.reduceList = new ColorNode[9];
        int i = 0;
        while (true) {
            ColorNode[] colorNodeArr = this.reduceList;
            if (i >= colorNodeArr.length) {
                break;
            }
            colorNodeArr[i] = null;
            i++;
        }
        this.numNodes = 0;
        this.maxNodes = 0;
        this.root = null;
        this.currSize = 0;
        this.currLevel = 8;
        int width = this.src.getWidth();
        int height = this.src.getHeight();
        int minX = this.src.getMinX();
        int minY = this.src.getMinY();
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                Color srcColor = getSrcColor(((width - i3) + minX) - 1, ((height - i2) + minY) - 1);
                if (this.transparency != 1 && srcColor.getAlpha() != 255) {
                    this.transColor = insertNode(this.transColor, srcColor, 0);
                } else {
                    this.root = insertNode(this.root, srcColor, 0);
                }
                if (this.currSize > this.requiredSize) {
                    reduceTree();
                }
            }
        }
    }

    protected ColorNode insertNode(ColorNode colorNode, Color color, int i) {
        if (colorNode == null) {
            colorNode = new ColorNode();
            int i2 = this.numNodes + 1;
            this.numNodes = i2;
            if (i2 > this.maxNodes) {
                this.maxNodes = i2;
            }
            colorNode.level = i;
            colorNode.isLeaf = i > 8;
            if (colorNode.isLeaf) {
                this.currSize++;
            }
        }
        colorNode.colorCount++;
        colorNode.red += color.getRed();
        colorNode.green += color.getGreen();
        colorNode.blue += color.getBlue();
        if (!colorNode.isLeaf) {
            int branchIndex = getBranchIndex(color, i);
            if (colorNode.children[branchIndex] == null) {
                colorNode.childCount++;
                if (colorNode.childCount == 2) {
                    colorNode.nextReducible = this.reduceList[i];
                    this.reduceList[i] = colorNode;
                }
            }
            colorNode.children[branchIndex] = insertNode(colorNode.children[branchIndex], color, i + 1);
        }
        return colorNode;
    }

    protected IndexColorModel getIndexColorModel() {
        int i = this.currSize;
        int i2 = this.transparency;
        if (i2 != 1) {
            i++;
        }
        int i3 = i;
        byte[] bArr = new byte[i3];
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        this.palette = new ColorNode[i3];
        findPaletteEntry(this.root, i2 != 1 ? 1 : 0, bArr, bArr2, bArr3);
        if (this.transparency != 1) {
            return new IndexColorModel(8, i3, bArr, bArr2, bArr3, 0);
        }
        return new IndexColorModel(8, this.currSize, bArr, bArr2, bArr3);
    }

    protected int findPaletteEntry(ColorNode colorNode, int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (colorNode.isLeaf) {
            bArr[i] = (byte) (colorNode.red / colorNode.colorCount);
            bArr2[i] = (byte) (colorNode.green / colorNode.colorCount);
            bArr3[i] = (byte) (colorNode.blue / colorNode.colorCount);
            colorNode.paletteIndex = i;
            this.palette[i] = colorNode;
            return i + 1;
        }
        int i2 = i;
        for (int i3 = 0; i3 < 8; i3++) {
            if (colorNode.children[i3] != null) {
                i2 = findPaletteEntry(colorNode.children[i3], i2, bArr, bArr2, bArr3);
            }
        }
        return i2;
    }

    protected int getBranchIndex(Color color, int i) {
        if (i > 8 || i < 0) {
            throw new IllegalArgumentException("Invalid octree node depth: " + i);
        }
        int i2 = 8 - i;
        return (((color.getBlue() & 255) >> i2) & 1) | ((((color.getRed() & 255) >> i2) & 1) << 2) | ((((color.getGreen() & 255) >> i2) & 1) << 1);
    }

    protected void reduceTree() {
        ColorNode[] colorNodeArr;
        int length = this.reduceList.length - 1;
        while (true) {
            colorNodeArr = this.reduceList;
            if (colorNodeArr[length] != null || length < 0) {
                break;
            } else {
                length--;
            }
        }
        ColorNode colorNode = colorNodeArr[length];
        if (colorNode == null) {
            return;
        }
        int i = colorNode.colorCount;
        ColorNode colorNode2 = colorNode;
        while (colorNode.nextReducible != null) {
            if (i > colorNode.nextReducible.colorCount) {
                i = colorNode.colorCount;
                colorNode2 = colorNode;
            }
            colorNode = colorNode.nextReducible;
        }
        ColorNode[] colorNodeArr2 = this.reduceList;
        if (colorNode2 == colorNodeArr2[length]) {
            colorNodeArr2[length] = colorNode2.nextReducible;
        } else {
            ColorNode colorNode3 = colorNode2.nextReducible;
            colorNode2.nextReducible = colorNode3.nextReducible;
            colorNode2 = colorNode3;
        }
        if (colorNode2.isLeaf) {
            return;
        }
        int leafChildCount = colorNode2.getLeafChildCount();
        colorNode2.isLeaf = true;
        this.currSize -= leafChildCount - 1;
        int i2 = colorNode2.level;
        for (int i3 = 0; i3 < 8; i3++) {
            colorNode2.children[i3] = freeTree(colorNode2.children[i3]);
        }
        colorNode2.childCount = 0;
    }

    protected ColorNode freeTree(ColorNode colorNode) {
        if (colorNode == null) {
            return null;
        }
        for (int i = 0; i < 8; i++) {
            colorNode.children[i] = freeTree(colorNode.children[i]);
        }
        this.numNodes--;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public class ColorNode {
        public long blue;
        public int colorCount;
        public long green;
        ColorNode nextReducible;
        public int paletteIndex;
        public long red;
        public boolean isLeaf = false;
        public int level = 0;
        public int childCount = 0;
        ColorNode[] children = new ColorNode[8];

        public ColorNode() {
            for (int i = 0; i < 8; i++) {
                this.children[i] = null;
            }
            this.colorCount = 0;
            this.blue = 0L;
            this.green = 0L;
            this.red = 0L;
            this.paletteIndex = 0;
        }

        public int getLeafChildCount() {
            int i = 0;
            if (this.isLeaf) {
                return 0;
            }
            int i2 = 0;
            while (true) {
                ColorNode[] colorNodeArr = this.children;
                if (i >= colorNodeArr.length) {
                    return i2;
                }
                if (colorNodeArr[i] != null) {
                    i2 = colorNodeArr[i].isLeaf ? i2 + 1 : i2 + colorNodeArr[i].getLeafChildCount();
                }
                i++;
            }
        }

        public int getRGB() {
            int i = (int) this.red;
            int i2 = this.colorCount;
            return (((i / i2) & 255) << 16) | (-16777216) | (((((int) this.green) / i2) & 255) << 8) | ((((int) this.blue) / i2) & 255);
        }
    }
}
