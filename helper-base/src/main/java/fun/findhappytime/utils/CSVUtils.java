package fun.findhappytime.utils;

import java.io.*;
import java.util.List;

/**
 * CSV文件导出工具类
 * Created by zhangshu on 2017/9/14.
 */

public class CSVUtils {

    /**
     * CSV文件生成方法
     *
     * @param head
     * @param dataList
     * @param outPutPath
     * @param filename
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }



   /* public void exportExcel(HttpServletRequest request, HttpServletResponse response, KhxxCxVO vo) throws IOException{
        File csvFile = createCSVFile(request,vo);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(csvFile.getName(), "UTF-8"));

        response.setHeader("Content-Length", String.valueOf(csvFile.length()));

        bis = new BufferedInputStream(new FileInputStream(csvFile));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        while (true) {
            int bytesRead;
            if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) break;
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }*/


    /*public File createCSVFile(HttpServletRequest request,KhxxCxVO vo){

        vo.setKhxm(StringUtil.formatDbLikeValue(vo.getKhxm()));

        String yybh = ContextUtil.getLoginUser().getUserId();
        String cur_ssjg = ContextUtil.getLoginUser().getUserUnit();
        String unitPath = ContextUtil.getLoginUser().getUnitPath();

        IPStaffVO staff = ipStaffService.findStaffByKey(yybh);

        String yhlx = staff.getYhlx();

        if((!cur_ssjg.equals(unitPath)) && yhlx.equals("2")){
            vo.setCur_path(StringUtil.formatDbLeftLikeValue(unitPath.trim()));
        }else if(yhlx.equals("1")){
            vo.setCur_ssjg(cur_ssjg.trim());
        }

        // 设置表格头
        Object[] head = {"客户姓名", "证件类型", "证件号码", "银行账号", "理财账号", "客户类型", "风险等级", "归属状况", "归属机构", "客户经理", "营销比例(%)" };
        List<Object> headList = Arrays.asList(head);

        List<KhxxCxVO> list = iKhxxCxService.findAllInfos(vo, Integer.MAX_VALUE, 0);

        // 码表取出证件类型
        Map<String, String> zjlx_map = new HashMap<String, String>();
        List<IPCodeInfoVO> zjlx_list = directoryService.findInfoListByTypeCode("zjlx", null);
        if ((zjlx_list != null) && (zjlx_list.size() > 0)){
            for (Iterator i$ = zjlx_list.iterator(); i$.hasNext(); ) {
                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
                zjlx_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
            }
        }
        Map<String, String> khlx_map = new HashMap<String, String>();
        List<IPCodeInfoVO> khlx_list = directoryService.findInfoListByTypeCode("khlx", null);
        if ((khlx_list != null) && (khlx_list.size() > 0)){
            for (Iterator i$ = khlx_list.iterator(); i$.hasNext(); ) {
                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
                khlx_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
            }
        }
        Map<String, String> fxdj_map = new HashMap<String, String>();
        List<IPCodeInfoVO> fxdj_list = directoryService.findInfoListByTypeCode("fxdj", null);
        if ((fxdj_list != null) && (fxdj_list.size() > 0)){
            for (Iterator i$ = fxdj_list.iterator(); i$.hasNext(); ) {
                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
                fxdj_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
            }
        }
        Map<String, String> gszk_map = new HashMap<String, String>();
        List<IPCodeInfoVO> gszk_list = directoryService.findInfoListByTypeCode("gszk", null);
        if ((gszk_list != null) && (gszk_list.size() > 0)){
            for (Iterator i$ = gszk_list.iterator(); i$.hasNext(); ) {
                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
                gszk_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
            }
        }
        // 设置数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for (int i = 0; i < list.size(); i++) {
            rowList = new ArrayList<Object>();
            KhxxCxVO kc_vo = list.get(i);
            //rowList.add(i + 1);
            rowList.add(kc_vo.getKhxm());
            rowList.add(StringUtil.nullToSpace(zjlx_map.get(kc_vo.getZjlx().trim())));

//                String zjhm= kc_vo.getZjhm();
//                System.out.println("zjhm----------"+zjhm);
//                DecimalFormat df = new DecimalFormat("#");//转换成整型
//                String zjhm_2 = df.format(zjhm);
//                System.out.println("zjhm2----------"+zjhm_2);
//                String zjhm_str = String.format("%.0f",kc_vo.getZjhm());
//                System.out.println("zjhmstr----------"+zjhm_str);

            rowList.add(kc_vo.getZjhm());
            rowList.add(kc_vo.getZhdh());
            rowList.add(kc_vo.getLczh());
            rowList.add(StringUtil.nullToSpace(khlx_map.get(kc_vo.getKhlx().trim())));
            rowList.add(StringUtil.nullToSpace(fxdj_map.get(kc_vo.getFxdj().trim())));
            rowList.add(StringUtil.nullToSpace(gszk_map.get(kc_vo.getGszk().trim())));
            rowList.add(kc_vo.getGsjgmc());
            rowList.add(kc_vo.getGsjl());
            rowList.add(kc_vo.getYxbl());
            //String cjsj = DateTimeUtil.formatDateTime(kc_vo.getCjsj());
            //rowList.add(cjsj);
            dataList.add(rowList);
        }

        // 导出文件路径
        String downloadFilePath = "C:" + File.separator + "cap4j" + File.separator + "download" + File.separator;
        IPCodeInfoVO codeInfoVO = directoryService.findInfoByTypeCodeAndInfoCode(
                CFNConstants.PLATFORM_CONFIG, CFNConstants.PLATFORM_CONFIG_DOWNLOAD_PATH);
        if (codeInfoVO != null && !StringUtils.isEmpty(codeInfoVO.getMbtmz())) {
            downloadFilePath = codeInfoVO.getMbtmz();
        }

//            String downloadFilePath = request.getSession().getServletContext().getRealPath("/exportload");

        // 导出文件名称
        String datetimeStr = DateTimeUtil.formatDate(new Date(), "yyyyMMddHHmmss");
        String fileName = "客户列表_" + datetimeStr;

//            String fileName = "";
//            try {
//                fileName = URLDecoder.decode("khxxCx_list","utf-8");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        // 导出CSV文件
        File csvFile = CSVUtils.createCSVFile(headList, dataList, downloadFilePath, fileName);

        return csvFile;
    }*/
}