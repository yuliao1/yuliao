package com.util.ikanalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.MusicInfo;

/**
 * WPS文档解析类-目前只有表格解析.xlsx文件
 * 
 * @author Administrator
 * 
 */
public class WPSFileUtil {
	public static void main(String[] args) {
//		String filePath = "E:\\大学生软件杯\\基于聊天机器人的数据查询系统\\doc\\歌曲的流派.xlsx";
//		String path = "MusicFiles";
//		File files = new File(path);
//		File[] fs = files.listFiles();
//		List<MusicInfo> list = new ArrayList<MusicInfo>();
//		for (File file : fs) {
//			try {
//				System.out.println("read:" + file.getAbsolutePath());
//				List<MusicInfo> tempList = readExcelCreateCategory(file
//						.getCanonicalPath());
//				list.addAll(tempList);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//		System.out.println("size:" + list.size());
//		out(list);
//		getAllExcelData();
		// readExcelCreateCategory(filePath);
	}

	private static void out(List<MusicInfo> list) {
		for (MusicInfo musicInfo : list) {
			if(musicInfo.getCreateDate()==null)
				System.out.println(musicInfo);
		}
	}
	/**
	 * 获取xlsx文档中的所有音乐数据
	 * @return
	 */
	public static List<MusicInfo> getAllExcelData(String path){
		path =path+ "/MusicFiles";
		File files = new File(path);
		File[] fs = files.listFiles();
		System.out.println("读到的文件数量："+fs.length);
		List<MusicInfo> list = new ArrayList<MusicInfo>();
		if(fs!=null)
		for (File file : fs) {
			try {
				System.out.println("read:" + file.getAbsolutePath());
				List<MusicInfo> tempList = readExcelCreateCategory(file
						.getCanonicalPath());
				list.addAll(tempList);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return list;
	}
	
	/**
	 * 方法描述: 读取Excel 文件 并 进行解析
	 * 
	 * @param fileName
	 *            Excel 地址
	 * @author Andy 2014-10-29 下午02:41:29
	 */
	private static List<MusicInfo> readExcelCreateCategory(String fileName) {
		File file = new File(fileName);
		List<MusicInfo> list = new ArrayList<MusicInfo>();
		if (file != null) {
			try {
				InputStream input = new FileInputStream(file); // 建立输入流
				Workbook wb = null;
				wb = new XSSFWorkbook(input);
				// wb = new HSSFWorkbook(input); // office 2003版本、WPS版本 用这个方法解析
				// System.out.println("表单数量："+wb.getNumberOfSheets());
				if (wb.getNumberOfSheets() > 0) { // 表单数 必须大于 0
				// System.out.println("表单名称"+wb.getSheetName(0));//获取第一个表单的 名称
					Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
					Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
					while (rows.hasNext()) {
						Row row = rows.next(); // 获得行数据
						// System.out.println("Row #" + row.getRowNum());
						// //获得行号从0开始
						Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
						MusicInfo info = new MusicInfo();
						int i = 0;
						boolean canAdd = true;
						while (cells.hasNext()) {
							Cell cell = cells.next();
							// System.out.print("Cell #" +
							// cell.getColumnIndex()); //获取 列 数
							Object obj = null;
							switch (cell.getCellType()) { // 根据cell中的类型来输出数据
							case HSSFCell.CELL_TYPE_NUMERIC:
								obj = cell.getNumericCellValue();
								break;
							case HSSFCell.CELL_TYPE_STRING:
								obj = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								obj = cell.getBooleanCellValue();
								break;
							case HSSFCell.CELL_TYPE_FORMULA:
								obj = cell.getCellFormula();
								break;
							default:
								obj = "unsuported sell type";
								break;
							}
							
							switch (i) {
							case 0:
								info.setMusicName(obj.toString().trim());
								break;
							case 1:
								info.setSinger(obj.toString().trim());
								break;
							case 2:
								info.setSex(obj.toString().trim());
								break;
							case 3:
								info.setAlbum(obj.toString().trim());
								break;
							case 4:
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyy/MM/dd");
								try {
									info.setCreateDate(dateFormat.parse(obj
											.toString().trim()));
								} catch (ParseException e) {
//									e.printStackTrace();
									System.err.println("有数据不能识别，请查看是否需要重新录入："+obj.toString());
									canAdd=false;
									break;
								}
								break;
							case 5:
								info.setClasses(obj.toString().trim());
								break;
							}
							i++;
						}
						if(canAdd)
							list.add(info);
					}
				} else {
					System.out.println("status:false" + "info表单数不能为0！");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
}
