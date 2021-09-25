/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadorLogRel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Azucena
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta1 = "/Users/Martha Jazmin/Documents/NetBeansProjects/ConversionColores/src/main/java/img/Imagen11.png";
        String ruta2 = "/Users/Martha Jazmin/Documents/NetBeansProjects/ConversionColores/src/main/java/img/Imagen22.png";
        String ruta3 = "/Users/Martha Jazmin/Documents/NetBeansProjects/ConversionColores/src/main/java/img/Imagen.jpg";

        System.out.println("¿Qué tipo de operación desea aplicar a las imágenes?");
        System.out.println("Introduzca L de lógicos o R de racionales:");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine();
        String tipoL;
        String tipoR;
        int umbral;
        BufferedImage img1 = null;
        BufferedImage img2 = null;
        BufferedImage img3 = null;

        try {
            img1 = ImageIO.read(new File(ruta1));
            img2 = ImageIO.read(new File(ruta2));
            img3 = ImageIO.read(new File(ruta3));

            if (respuesta.equals("L")) {
                System.out.println(" ");
                System.out.println("Tipos:");
                System.out.println("and");
                System.out.println("or");
                System.out.println(" ");
                System.out.println("Introduzca el nombre de uno de los tipos de operadores lógicos:");
                tipoL = scanner.nextLine();
                System.out.println("Introduzca el valor del umbral, el valor puede ser de 0 a 225:");
                umbral = scanner.nextInt();

                ImageIO.write(logicos(tipoL, img1, img2, umbral), "png", 
                        new File("/Users/Martha Jazmin/Documents/NetBeansProjects/ConversionColores/src/main/java/img/ImagL.png"));

            } else if (respuesta.equals("R")) {
                System.out.println("Tipos:");
                System.out.println("identidad");
                System.out.println("inverso");
                System.out.println("umbral");
                System.out.println("umbral binario");
                System.out.println("umbral binario invertido");
                System.out.println("umbral de la escala de grises");
                System.out.println("umbral de la escala de grises invertido");
                System.out.println("extension");
                System.out.println("reduccion del nivel de gris");
                System.out.println("adicion");
                System.out.println("substraccion");
                System.out.println(" ");
                System.out.println("Introduzca el nombre de uno de los tipos de operadores relacionales:");
                tipoR = scanner.nextLine();

                ImageIO.write(relacionales(tipoR, img3, img1, img2), "png", 
                        new File("/Users/Martha Jazmin/Documents/NetBeansProjects/ConversionColores/src/main/java/img/ImagR.png"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static BufferedImage relacionales(String tipoR, BufferedImage img3, BufferedImage img1, BufferedImage img2) {
        BufferedImage imgGris = null;
        BufferedImage imgGris1 = null;
        BufferedImage imgGris2 = null;
        ColorConvertOp ccop = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        imgGris = ccop.filter(img3, null);
        imgGris2 = ccop.filter(img2, null);
        imgGris1 = ccop.filter(img1, null);
        Color White = new Color(255, 255, 255); // Color blanco
        Color Black = new Color(0, 0, 0); // Color negro
        int rgbW = White.getRGB();
        int rgbB = Black.getRGB();
        Color color;
        int colores;
        int colores1;
        int colores2;

        if (tipoR.equals("identidad")) {
            return img3;

        } else if (tipoR.equals("inverso")) {
            //ancho
            for (int x = 0; x < img3.getWidth(); x++) {
                //alto
                for (int y = 0; y < img3.getHeight(); y++) {
                    colores = imgGris.getRGB(x, y);
                    img3.setRGB(x, y, 255 - colores);
                }
            }
            return img3;

        } else if (tipoR.equals("umbral")) {
            int umbral = 90;
            //ancho
            for (int x = 0; x < imgGris.getWidth(); x++) {
                //alto
                for (int y = 0; y < imgGris.getHeight(); y++) {

                    color = new Color(imgGris.getRGB(x, y));

                    if (color.getRed() <= umbral) {
                        img3.setRGB(x, y, rgbB);

                    } else {
                        img3.setRGB(x, y, rgbW);
                    }
                }
            }
            return img3;

        } else if (tipoR.equals("umbral binario")) {

            int umbral1 = 100;
            int umbral2 = 180;

            //ancho
            for (int x = 0; x < imgGris.getWidth(); x++) {
                //alto
                for (int y = 0; y < imgGris.getHeight(); y++) {

                    color = new Color(imgGris.getRGB(x, y));

                    if (umbral1 < color.getRed()) {
                        if (color.getRed() < umbral2) {
                            img3.setRGB(x, y, rgbB);
                        }
                    } else {
                        img3.setRGB(x, y, rgbW);
                    }
                }
            }

            return img3;

        } else if (tipoR.equals("umbral binario invertido")) {
            int umbral1 = 100;
            int umbral2 = 180;

            //ancho
            for (int x = 0; x < imgGris.getWidth(); x++) {
                //alto
                for (int y = 0; y < imgGris.getHeight(); y++) {

                    color = new Color(imgGris.getRGB(x, y));

                    if (umbral1 < color.getRed()) {
                        if (color.getRed() < umbral2) {
                            img3.setRGB(x, y, rgbW);
                        }
                    } else {
                        img3.setRGB(x, y, rgbB);
                    }
                }
            }
            return img3;

        } else if (tipoR.equals("umbral de la escala de grises")) {
            int umbral1 = 100;
            int umbral2 = 180;

            //ancho
            for (int x = 0; x < imgGris.getWidth(); x++) {
                //alto
                for (int y = 0; y < imgGris.getHeight(); y++) {

                    colores = imgGris.getRGB(x, y);
                    color = new Color(imgGris.getRGB(x, y));

                    if (umbral1 < color.getRed()) {
                        if (color.getRed() < umbral2) {
                            img3.setRGB(x, y, colores);
                        }
                    } else {
                        img3.setRGB(x, y, rgbW);
                    }
                }
            }

            return img3;

        } else if (tipoR.equals("umbral de la escala de grises invertido")) {
            int umbral1 = 100;
            int umbral2 = 180;

            //ancho
            for (int x = 0; x < imgGris.getWidth(); x++) {
                //alto
                for (int y = 0; y < imgGris.getHeight(); y++) {

                    colores = imgGris.getRGB(x, y);
                    color = new Color(imgGris.getRGB(x, y));

                    if (umbral1 < color.getRed()) {
                        if (color.getRed() < umbral2) {
                            img3.setRGB(x, y, 255 - colores);
                        }
                    } else {
                        img3.setRGB(x, y, rgbW);
                    }
                }
            }

            return img3;

        } else if (tipoR.equals("extension")) {
            int umbral1 = 100;
            int umbral2 = 180;
            //ancho
            for (int x = 0; x < imgGris.getWidth(); x++) {
                //alto
                for (int y = 0; y < imgGris.getHeight(); y++) {

                    colores = imgGris.getRGB(x, y);
                    color = new Color(imgGris.getRGB(x, y));

                    if (umbral1 < color.getRed()) {
                        if (color.getRed() < umbral2) {
                            img3.setRGB(x, y, (colores - umbral1) * (255 / (umbral2 - umbral1)));
                        }
                    } else {
                        img3.setRGB(x, y, rgbB);
                    }
                }
            }

            return img3;

        } else if (tipoR.equals("reduccion del nivel de gris")) {
            Color color1 = new Color(50, 50, 50);
            Color color2 = new Color(75, 75, 75);
            Color color3 = new Color(100, 100, 100);
            Color color4 = new Color(125, 125, 125);
            Color color5 = new Color(150, 150, 150);
            Color color6 = new Color(175, 175, 175);
            int rgb1 = color1.getRGB();
            int rgb2 = color2.getRGB();
            int rgb3 = color3.getRGB();
            int rgb4 = color4.getRGB();
            int rgb5 = color5.getRGB();
            int rgb6 = color6.getRGB();
            int umbral1 = 50;
            int umbral2 = 75;
            int umbral3 = 100;
            int umbral4 = 125;
            int umbral5 = 150;
            int umbral6 = 175;

            //ancho
            for (int x = 0; x < imgGris.getWidth(); x++) {
                //alto
                for (int y = 0; y < imgGris.getHeight(); y++) {

                    colores = imgGris.getRGB(x, y);
                    color = new Color(imgGris.getRGB(x, y));

                    if (color.getRed() <= umbral1) {
                        img3.setRGB(x, y, rgbB);

                    } else if (umbral1 < color.getRed()) {
                        if (color.getRed() <= umbral2) {
                            img3.setRGB(x, y, rgb1);
                        }

                    } else if (umbral2 < color.getRed()) {
                        if (color.getRed() <= umbral3) {
                            img3.setRGB(x, y, rgb2);
                        }

                    } else if (umbral3 < color.getRed()) {
                        if (color.getRed() <= umbral4) {
                            img3.setRGB(x, y, rgb3);
                        }

                    } else if (umbral4 < color.getRed()) {
                        if (color.getRed() <= umbral5) {
                            img3.setRGB(x, y, rgb4);
                        }

                    } else if (umbral5 < color.getRed()) {
                        if (color.getRed() <= umbral6) {
                            img3.setRGB(x, y, rgb5);
                        }

                    } else if (umbral6 < color.getRed()) {
                        if (color.getRed() <= rgbW) {
                            img3.setRGB(x, y, rgb6);
                        }

                    }
                }
            }

            return img3;

        } else if (tipoR.equals("adicion")) {
            //ancho
            for (int x = 0; x < img3.getWidth(); x++) {
                //alto
                for (int y = 0; y < img3.getHeight(); y++) {

                    colores1 = imgGris1.getRGB(x, y);
                    colores2 = imgGris2.getRGB(x, y);
                    img3.setRGB(x, y, ((colores1 + colores2) / 2));
                }
            }

            return img3;

        } else {//substracción
            //ancho
            for (int x = 0; x < img3.getWidth(); x++) {
                //alto
                for (int y = 0; y < img3.getHeight(); y++) {

                    colores1 = imgGris1.getRGB(x, y);
                    colores2 = imgGris2.getRGB(x, y);
                    img3.setRGB(x, y, (Math.abs(colores1 - colores2)));
                }
            }

            return img3;
        }
    }

    public static BufferedImage logicos(String tipoL, BufferedImage img1, BufferedImage img2, int umbral) {
        BufferedImage imgGris1 = null;
        BufferedImage imgGris2 = null;
        ColorConvertOp ccop = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        imgGris2 = ccop.filter(img2, null);
        imgGris1 = ccop.filter(img1, null);
        Color White = new Color(255, 255, 255); // Color blanco
        Color Black = new Color(0, 0, 0); // Color negro
        int rgbW = White.getRGB();
        int rgbB = Black.getRGB();
        Color color1;
        Color color2;
        int coloresL1;//para las imagenes ya en binario
        int coloresL2;

        if (tipoL.equals("and")) {

            //ancho
            for (int x = 0; x < img1.getWidth(); x++) {
                //alto
                for (int y = 0; y < img1.getHeight(); y++) {

                    color1 = new Color(imgGris1.getRGB(x, y));
                    color2 = new Color(imgGris2.getRGB(x, y));

                    if (color1.getRed() < umbral) {//converitr a binario

                        img1.setRGB(x, y, rgbW);

                    } else {

                        img1.setRGB(x, y, rgbB);
                    }
                    if (color2.getRed() < umbral) {

                        img2.setRGB(x, y, rgbW);

                    } else {

                        img2.setRGB(x, y, rgbB);
                    }

                    coloresL1 = img1.getRGB(x, y);
                    coloresL2 = img2.getRGB(x, y);

                    if (coloresL1 == coloresL2) {//comparar
                        img1.setRGB(x, y, coloresL1);
                    } else {
                        img1.setRGB(x, y, rgbB);
                    }
                }
            }
            return img1;

        } else { //or
            
            //ancho
            for (int x = 0; x < img1.getWidth(); x++) {
                //alto
                for (int y = 0; y < img1.getHeight(); y++) {

                    color1 = new Color(imgGris1.getRGB(x, y));
                    color2 = new Color(imgGris2.getRGB(x, y));

                    if (color1.getRed() < umbral) {//converitr a binario

                        img1.setRGB(x, y, rgbW);

                    } else {

                        img1.setRGB(x, y, rgbB);
                    }
                    if (color2.getRed() < umbral) {

                        img2.setRGB(x, y, rgbW);

                    } else {

                        img2.setRGB(x, y, rgbB);
                    }

                    coloresL1 = img1.getRGB(x, y);
                    coloresL2 = img2.getRGB(x, y);

                    if (coloresL1 == rgbW || coloresL2 == rgbW) {//comparar y asignar 1 o 0 al pixel
                        img1.setRGB(x, y, rgbW);
                    } else {
                        img1.setRGB(x, y, rgbB);
                    }
                }
            }
            return img1;
        }

    }
}
