package bsp.connection;

import bsp.fileloader.Record;
import bsp.fileloader.Slide;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.imageio.ImageIO;

public class Packer {
	
	/**
	 * Packing and Return the record's header in an byte array
	 * with the following order:
	 * 
	 *  <p>- File name's length
	 *  <p>- File name
	 *  <p>- Total slide count in this record
	 * */
	public static byte[] getRecordHeaderPak(Record record){
		String fileName = record.getFileName();
		int slideNum = record.getSlideCount();
		ByteBuffer buffer = ByteBuffer.allocate(8+fileName.length());
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		
		buffer.putInt(fileName.length());
		buffer.put(fileName.getBytes());
		buffer.putInt(slideNum);
		
		return buffer.array();
	}
	
	public static byte[] getSlidePak(Slide slide){
		String note = slide.getNote();
		ByteBuffer buffer = ByteBuffer.allocate(8+note.length());
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		
		buffer.putInt(slide.getSlide());
		buffer.putInt(note.length());
		buffer.put(note.getBytes());
		
		return buffer.array();
	}
	
	public static byte[] getPNGPak(java.io.File file){
		String name = file.getName();
		int fileSize = 0;
		java.io.FileInputStream fis;
		try {
			fis = new java.io.FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		java.io.ByteArrayOutputStream bio = new java.io.ByteArrayOutputStream();
		byte[] buff = new byte[2048];
		try {
			for(int readed;(readed=fis.read(buff))!=-1;){
				fileSize+=readed;
				System.out.println("Prepared "+fileSize+" bytes");
				bio.write(buff, 0, readed);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		buff = bio.toByteArray();
		assert(fileSize==buff.length);
		
		Packer.recoverPNG(buff);
		
		ByteBuffer buffer = ByteBuffer.allocate(8+name.length()+fileSize);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		
		buffer.putInt(name.length());
		buffer.put(name.getBytes());
		buffer.putInt(fileSize);
		buffer.put(buff);
		
		return buffer.array();
	}
	
	public static void recoverPNG(byte[] data){
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		BufferedImage bi=null;
		try {
			bi = ImageIO.read(bis);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		File out = new File("D:\\jj.jpg");
		
		try {
			ImageIO.write(bi, "jpg", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args){
		String a = "123456789";
		System.out.println(a.length());
		System.out.println(a.getBytes()[0]);
		try {
			System.out.println(a.getBytes("UTF-8").length);
			System.out.println(a.getBytes("UTF-8")[0]);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path="D:\\testPic.jpg";
		java.io.File file = new java.io.File(path);
		assert(file.exists());
		
		byte[] result = Packer.getPNGPak(file);
		
		System.out.println(result.length);
		
		//Packer.recoverPNG(result);
	}
}
