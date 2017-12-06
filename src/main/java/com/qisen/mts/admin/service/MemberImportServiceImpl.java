package com.qisen.mts.admin.service;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.constant.BeautyDoConsts;
import com.qisen.mts.common.dao.base.ItemDao;
import com.qisen.mts.common.dao.mem.CardDao;
import com.qisen.mts.common.dao.mem.CardRecordDao;
import com.qisen.mts.common.dao.mem.MemItemDao;
import com.qisen.mts.common.dao.mem.MemberDao;
import com.qisen.mts.common.dao.sys.EnterpriseDao;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.mem.Card;
import com.qisen.mts.common.model.entity.mem.CardRecord;
import com.qisen.mts.common.model.entity.mem.Item;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class MemberImportServiceImpl implements MemberImportService {
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private CardDao cardDao;

	@Autowired
	private CardRecordDao cardRecordDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private MemItemDao memItemDao;

	@Autowired
	private EnterpriseDao enterpriseDao;

	@SuppressWarnings("deprecation")
	@Override
	public BaseResponse memberImport(MultipartFile file, Integer eid, Integer sid) {
		BaseResponse response = new BaseResponse();
		List<JSONObject> objList = new ArrayList<JSONObject>();
		String tbuser = enterpriseDao.findTbuser(eid);
		InputStream in;
		Workbook workbook;
		double payCard = 0.0;
		double payPresent = 0.0;
		try {
			in = file.getInputStream();
			workbook = WorkbookFactory.create(in);
			Sheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row st = sheet.getRow(i + 1);
				JSONObject obj = new JSONObject();
				obj.put("eid", eid);
				obj.put("sid", sid);
				for (int j = 0; j < 17; j++) {
					Cell cell = st.getCell(j);
					String cellValue = "";
					if (cell != null) {
						int cellType = cell.getCellType();
						switch (cellType) {
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								Date d = cell.getDateCellValue();
								DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
								cellValue = formater.format(d);
							} else {
								cellValue = new DecimalFormat("0.##########").format(cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getRichStringCellValue().toString().trim();
							break;
						case Cell.CELL_TYPE_FORMULA:
							cellValue = String.valueOf(cell.getCellFormula()).trim();
							break;
						case Cell.CELL_TYPE_BLANK:
							cellValue = String.valueOf(cell.getRichStringCellValue().toString()).trim();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
							break;
						case Cell.CELL_TYPE_ERROR:
							cellValue = String.valueOf(cell.getErrorCellValue()).trim();
							break;
						}
					}
					boolean flag = true;
					if (j == 0 || j == 1) {
						if (cellValue.equals("")) {
							flag = false;
						}
					}
					if (flag) {// 第一列,第二列一定不能为空
						switch (j) {
						case 0:
							String cardTypeNo = cellValue;// 获取第i行，第1列的值(会员卡类型编号)
							obj.put("cardTypeNo", cardTypeNo);
							break;
						case 1:
							String cardNo = cellValue;// 获取第i行，第2列的值(会员卡号)
							obj.put("cardNo", cardNo);
							break;
						case 2:
							String mobile = cellValue;// 获取第i行，第3列的值(手机号码)
							obj.put("mobile", mobile);
							break;
						case 3:
							String name = cellValue;// 获取第i行，第4列的值(姓名)
							if ((name == null) || (name.trim().equals("")))
								name = "顾客";
							obj.put("name", name);
							break;
						case 4:
							String sex = cellValue;// 获取第i行，第5列的值(性别)
							if (sex.equals("1"))
								sex = "M";
							else if (sex.equals("0"))
								sex = "F";
							else
								sex = "F";
							obj.put("sex", sex);
							break;
						case 5:
							String sumCardFee = cellValue;
							if ((sumCardFee == null) || (sumCardFee.trim().equals("")))// 获取第i行，第6列的值(储值总额)
								sumCardFee = "0";
							obj.put("sumCardFee", sumCardFee);
							break;
						case 6:
							String cardFee = cellValue; // 获取第i行，第7列的值(卡内余额)
							if ((cardFee == null) || (cardFee.trim().equals("")))
								cardFee = "0";
							payCard += Double.parseDouble(cardFee);
							obj.put("cardFee", cardFee);
							break;
						case 7:
							String sumPresentFee = cellValue;// 获取第i行，第8列的值(赠送总额)
							if ((sumPresentFee == null) || (sumPresentFee.trim().equals("")))
								sumPresentFee = "0";
							obj.put("sumPresentFee", sumPresentFee);
							break;
						case 8:
							String presentFee = cellValue;
							if ((presentFee == null) || (presentFee.trim().equals("")))// 获取第i行，第9列的值(赠送余额)
								presentFee = "0";
							payPresent += Double.parseDouble(presentFee);
							obj.put("presentFee", presentFee);
							break;
						case 9:
							String discount = cellValue;// 获取第i行，第10列的值(折扣)
							if ((discount == null) || (discount.trim().equals("")))
								discount = "0";
							obj.put("discount", discount);
							break;
						case 10:
							String cashPwd = cellValue;// 获取第i行，第11列的值(会员卡密码)
							obj.put("cashPwd", cashPwd);
							break;
						case 11:
							Date birthDay = null;
							if (!cellValue.equals("")) {
								birthDay = cell.getDateCellValue();// 获取第i行，第12列的值(生日)
							}
							obj.put("birthDay", birthDay);
							break;
						case 12:
							String remark = cellValue;// 获取第i行，第13列的值(备注)
							obj.put("remark", remark);
							break;
						case 13:
							String currpoint = cellValue;// 获取第i行，第14列的值(积分)
							if ((currpoint == null) || (currpoint.trim().equals("")))
								currpoint = "0";
							obj.put("currpoint", currpoint);
							break;
						case 14:
							String typeNo = cellValue;// 获取第i行，第15列的值(会员分类编号)
							obj.put("typeNo", typeNo);
							break;
						case 15:
							Date inputDate = null;
							if (!cellValue.equals("")) {
								inputDate = cell.getDateCellValue();// 获取第i行，第16列的值(注册日期)
							} else {
								inputDate = (new java.sql.Timestamp(System.currentTimeMillis()));
							}
							obj.put("inputDate", inputDate);
							break;
						case 16:
							Date validDate = null;
							if (!cellValue.equals("")) {
								validDate = cell.getDateCellValue();// 获取第i行，第17列的值(到期日期)
							}
							obj.put("validDate", validDate);
							break;
						default:
							break;
						}
					}
				}
				objList.add(obj);
			}

		} catch (Exception e) {
			response.setResult(ResultCode.FAILED);
		}
		for (JSONObject obj : objList) {// 导入会员资料返回id
			Member member = JSONObject.toJavaObject(obj, Member.class);
			this.memberDao.importMember(member);
			obj.put("memId", member.getId());
		}
		this.cardDao.importCard(objList);// 导入卡表

		/**
		 * 插入卡金变动流水
		 */
		CardRecord cardRecord = new CardRecord();
		cardRecord.setSid(sid);
		cardRecord.setMemSid(sid);
		cardRecord.setPayCard(payCard);
		cardRecord.setPayPresent(payPresent);
		cardRecord.setType(BeautyDoConsts.CARDRECORD_TYPE_IMPROT);
		cardRecord.setStype(1);
		this.cardRecordDao.create(tbuser, eid, cardRecord);
		return response;
	}

	@Override
	public CommObjResponse<JSONObject> treatImport(MultipartFile file, Integer eid, Integer sid) {
		CommObjResponse<JSONObject> response = new CommObjResponse<JSONObject>();
		JSONObject body = new JSONObject();
		StringBuffer checkItemNo = new StringBuffer("项目编号：");
		StringBuffer nocardNo = new StringBuffer("卡号：");
		StringBuffer recardNo = new StringBuffer("卡号：");
		List<JSONObject> objList = new ArrayList<JSONObject>();
		InputStream in;
		Workbook workbook;
		try {
			in = file.getInputStream();
			workbook = WorkbookFactory.create(in);
			Sheet sheet = workbook.getSheetAt(0);
			boolean isOk = true;
			for (int i = 2; i < sheet.getLastRowNum(); i++) {
				Row st = sheet.getRow(i + 1);
				JSONObject obj = new JSONObject();
				obj.put("eid", eid);
				obj.put("sid", sid);
				for (int j = 0; j < 8; j++) {
					Cell cell = st.getCell(j);
					String cellValue = "";
					if (cell != null) {
						int cellType = cell.getCellType();
						switch (cellType) {
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								Date d = cell.getDateCellValue();
								DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
								cellValue = formater.format(d);
							} else {
								cellValue = new DecimalFormat("0.##########").format(cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getRichStringCellValue().toString().trim();
							break;
						case Cell.CELL_TYPE_FORMULA:
							cellValue = String.valueOf(cell.getCellFormula()).trim();
							break;
						case Cell.CELL_TYPE_BLANK:
							cellValue = String.valueOf(cell.getRichStringCellValue().toString()).trim();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
							break;
						case Cell.CELL_TYPE_ERROR:
							cellValue = String.valueOf(cell.getErrorCellValue()).trim();
							break;
						}
						boolean flag = true;
						if (j == 0 || j == 1) {
							if (cellValue.equals("")) {
								flag = false;
							}
						}
						if (flag) {// 项目编号和卡号必须填
							switch (j) {
							case 0:// 获取第i行，第1列的值(项目编号)
								String itemNo = cell.getStringCellValue().trim();
								obj.put("itemNo", itemNo);
								// 检测项目编号是否存在
								int count = this.itemDao.checkItemNo(eid, itemNo);
								if (count == 0) {
									isOk = false;
									checkItemNo.append(itemNo + ",");
								}
								break;
							case 1:// 获取第i行，第2列的值(会员卡号)
								String cardNo = cell.getStringCellValue().trim();
								obj.put("cardNo", cardNo);
								break;
							case 2:// 获取第i行，第3列的值(剩余次数)
								String leaveTimes = cell.getStringCellValue().trim();
								obj.put("leaveTimes", leaveTimes);
								break;
							case 3:// 获取第i行，第4列的值(总次数)
								String sumTimes = cell.getStringCellValue().trim();
								if ((sumTimes == null) || (sumTimes.trim().equals("")))
									sumTimes = obj.getString("leaveTimes");
								obj.put("sumTimes", sumTimes);
								break;
							case 4:// 获取第i行，第5列的值(剩余金额)
								String leaveMoney = cell.getStringCellValue().trim();
								obj.put("leaveMoney", leaveMoney);
								break;
							case 5:// 获取第i行，第6列的值(储值总额)
								String sumMoney = cell.getStringCellValue().trim();
								if ((sumMoney == null) || (sumMoney.trim().equals("")))
									sumMoney = "0";
								obj.put("sumMoney", sumMoney);
								break;
							case 6: // 获取第i行，第7列的值(手机)
								String mobile = cell.getStringCellValue().trim();
								obj.put("mobile", mobile);
								break;
							case 7: // 获取第i行，第8列的值(卡类型编号)
								String cardTypeNo = cell.getStringCellValue().trim();
								obj.put("cardTypeNo", cardTypeNo);
								break;
							case 8:// 获取第i行，第9列的值(失效日期)
								String validDate = cell.getStringCellValue().trim();
								obj.put("validDate", validDate);
								break;
							default:
								break;
							}
						}
					}
					
				}
				// 查询卡号是否唯一
				List<Card> cards = this.cardDao.checkCardid(eid, sid, obj.getString("cardNo"), obj.getString("mobile"),  obj.getString("cardTypeNo"));
				if (cards.size() == 0) {
					isOk = false;
					nocardNo.append(obj.getString("cardNo") + ",");
				}
				if (cards.size() > 1) {
					isOk = false;
					recardNo.append( obj.getString("cardNo") + ",");
				}
				if (cards.size() == 1) {
					obj.put("memId", cards.get(0).getMemId());
					obj.put("memCardId", cards.get(0).getId());
				}
				objList.add(obj);
			}
			if (isOk) {
				if (objList.size() > 0) {// 开始导入
					for (JSONObject obj : objList) {
						List<Item> memItemlist = this.memItemDao.checkTreat(eid, sid, obj.getString("itemNo"),obj.getInteger("memCardId"));
						if(memItemlist.size()>1){
							body.put("dataError", "数据有问题");
							response.setResult(ResultCode.FAILED);
						}else if(memItemlist.size()==0){// 插入疗程
							obj.put("onceMoney", obj.getDouble("leaveMoney") / obj.getIntValue("leaveTimes"));
							this.memItemDao.importTreat(eid, sid, obj);
						}else{// 修改疗程
							obj.put("id", memItemlist.get(0).getId());
							obj.put("leaveMoney", obj.getDouble("leaveMoney") + memItemlist.get(0).getLeaveMoney());
							obj.put("sumMoney", obj.getDouble("sumMoney") +  memItemlist.get(0).getSumMoney());
							obj.put("leaveTimes", obj.getIntValue("leaveTimes") +  memItemlist.get(0).getLeaveTimes());
							obj.put("sumTimes", obj.getIntValue("sumTimes") +  memItemlist.get(0).getSumTimes());
							obj.put("onceMoney", obj.getDouble("leaveMoney") / obj.getIntValue("leaveTimes"));
							this.memItemDao.importUpdateTreat(eid, sid, obj);
						}
					}
				}
			} else {
				checkItemNo.append("不存在");
				nocardNo.append("不存在");
				recardNo.append("重复");
				body.put("checkItemNo", checkItemNo.toString());
				body.put("nocardNo", nocardNo.toString());
				body.put("recardNo", recardNo.toString());
				response.setResult(ResultCode.FAILED);
			}
		} catch (Exception e) {
			response.setResult(ResultCode.FAILED);
			e.printStackTrace();
		}
		response.setBody(body);
		return response;
	}

}
