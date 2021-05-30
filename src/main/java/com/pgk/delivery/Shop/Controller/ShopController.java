package com.pgk.delivery.Shop.Controller;

import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Shop.Pojo.Comment;
import com.pgk.delivery.Shop.Pojo.Commodity;
import com.pgk.delivery.Shop.Pojo.Shop;
import com.pgk.delivery.Shop.Service.ShopService;
import com.pgk.delivery.Util.ExcelUtil;
import com.pgk.delivery.Util.PassToken;
import com.pgk.delivery.Util.UUIDUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService service;

    @PassToken
    @RequestMapping("/queryAll.do")
    public Result<?> queryAll() {
        Result<?> shops = service.queryAll();
        return shops;
    }

    @PassToken
    @RequestMapping("/queryBuyerLikeShop.do")
    public Result<?> queryBuyerLikeShop(String buyerAccount) {
        Result<?> msg = service.queryBuyerLikeShop(buyerAccount);
        return msg;
    }

    @RequestMapping("/queryBuyerLikeShopInfo.do")
    public Result<?> queryBuyerLikeShopInfo(String buyerAccount) {
        Result<?> msg = service.queryBuyerLikeShopInfo(buyerAccount);
        return msg;
    }

    @RequestMapping("/addBuyerLikeShop.do")
    public Result<?> addBuyerLikeShop(String buyerAccount,int shopId) {
        Result<?> msg = service.addBuyerLikeShop(buyerAccount,shopId);
        return msg;
    }
    @RequestMapping("/deleteBuyerLikeShop.do")
    public Result<?> deleteBuyerLikeShop(String buyerAccount,int shopId) {
        Result<?> msg = service.deleteBuyerLikeShop(buyerAccount,shopId);
        return msg;
    }

    @PassToken
    @RequestMapping("/queryShopInfo.do")
    public Result<?> queryShopInfo(int shopId) {
        Result<?> shop = service.queryShopInfo(shopId);
        return shop;
    }

    @PassToken
    @RequestMapping("/queryByName.do")
    public Result<?> queryByName(String shopName) {
        Result<?> shop = service.queryByName(shopName);
        return shop;
    }
    @PassToken
    @RequestMapping("/queryByType.do")
    public Result<?> queryByType(int shopTypeId) {
        Result<?> shop = service.queryByType(shopTypeId);
        return shop;
    }

    @PassToken
    @RequestMapping("/queryById.do")
    public Result<?> queryById(int shopId ) {
            Result<?> shop = service.queryById(shopId);
            return shop;
    }


    @RequestMapping("/queryAllCommodity.do")
    public Result<?> queryAllCommodity(HttpServletRequest request, HttpServletResponse response, Integer accountUserId) {
        int accountLimit = (int) request.getAttribute("accountLimit");
        if (accountLimit == 4 || accountLimit == 3) {
            Result<?> commodity = service.queryAllCommodity(accountUserId);
            return commodity;
        } else {
            response.setStatus(403);
            return null;
        }
    }
    /**
     * 每次添加商品时的种类选项查询
     *
     * @return
     */
    @RequestMapping("/selectMenu.do")
    public Result<?> selectMenu() {
        Result<?> menu = service.selectMenu();
        return menu;
    }

    /**
     * 查询店铺种类
     * @return
     */
    @RequestMapping("/selectShopType.do")
    public Result<?> selectShopType() {
        Result<?> menu = service.selectShopType();
        return menu;
    }

    @RequestMapping("/addMenu.do")
    public Result<?> addMenu(String shopMenuName) {
        Result<?> menu = service.addMenu(shopMenuName);
        return menu;
    }


    @RequestMapping("/commodityAdd.do")
    public Result<?> commodityAdd(@RequestBody Commodity commodity) {
        Result<?> msg = service.commodityAdd(commodity);
        return msg;
    }

    @RequestMapping("/commodityEdit.do")
    public Result<?> commodityEdit(@RequestBody Commodity commodity) {
        Result<?> msg = service.commodityEdit(commodity);
        return msg;
    }

    /**
     * 查询店铺名
     *
     * @param sellerId
     * @return
     */
    @RequestMapping("/queryShopName.do")
    public Result<?> queryShopName(int sellerId) {
        Result<?> msg = service.queryShopName(sellerId);
        return msg;
    }

    /**
     * 查询店铺信息
     *
     * @param sellerId
     * @return
     */
    @RequestMapping("/selectShopInformation.do")
    public Result<?> selectShopInformation(int sellerId) {
        Result<?> shop = service.selectShopInformation(sellerId);
        return shop;
    }

    /**
     * 修改店铺信息
     *
     * @param shop
     * @return
     */
    @RequestMapping("/updateShopInformation.do")
    public Result<?> updateShopInformation(@RequestBody Shop shop) {
        Result<?> msg = service.updateShopInformation(shop);
        return msg;
    }

    /**
     * 导出excel表格
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportExcel.do")
    public void exportExcel(HttpServletResponse response, int accountUserId) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取数据
        List<Commodity> commodities = (List<Commodity>) service.queryAllCommodity(accountUserId).getResult();
        //excel标题
//        String[] title = {"用户Id", "商品Id", "commodityImg", "commodityMenuId", "commodityName"
//                ,"commodityNumber","commodityPrice","commodityShopId","shopMenuId","shopMenuName","shopName"};
        String[] title = {"商品编号", "商品名", "商品库存", "商品价格", "商品种类"};
        //excel文件名
        String fileName = "商品表" + System.currentTimeMillis() + ".xls";
        //sheet名
        String sheetName = "商品信息表";
        String content[][] = new String[commodities.size()][title.length];
        for (int i = 0; i < commodities.size(); i++) {
            Commodity obj = commodities.get(i);
            content[i][0] = String.valueOf(obj.getCommodityId());
            content[i][1] = obj.getCommodityName();
            content[i][2] = String.valueOf(obj.getCommodityNumber());
            content[i][3] = String.valueOf(obj.getCommodityPrice());
            content[i][4] = obj.getShopMenuName();
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @RequestMapping("/pictureDelete.do")
    public Result<?> pictureDelete(String path) {
        path = path.replace("http://localhost:8087/picture/", "");
        path = "D:/IdeaProject/Delivery/src/main/resources/static/picture/" + path;
        File file = new File(path);
        if (file.exists()) {//文件是否存在
            if (file.delete()) {//存在就删了
                return Result.success();
            } else {
                return Result.fail();
            }
        } else {
            System.out.println("文件不存在");
            return Result.fail();
        }
    }

    @RequestMapping("/pictureAdd.do")
    public Result<?> pictureAdd(@RequestParam("picture") MultipartFile picture) {
        String path = "D:/IdeaProject/newDelivery/src/main/resources/static/picture";
        File filePath = new File(path);
        //如果目录不存在，创建目录
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        //新名字
        String fileName = UUIDUtils.getUUID() + "." + type;
        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return Result.success("http://localhost:8087/picture/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @RequestMapping("/delectCommodity.do")
    public Result<?> delectCommodity(int commodityId, HttpServletRequest request, HttpServletResponse response) {
        int accountLimit = (int) request.getAttribute("accountLimit");
        if (accountLimit == 4 || accountLimit == 3) {
            Result<?> commoditys = service.delectCommodity(commodityId);
            if (commoditys.getCode() == -1) {
                response.setStatus(500);
                return null;
            }
            return Result.success();
        } else {
            response.setStatus(403);
            return null;
        }
    }


    /**
     * 新增评论
     *
     * @return
     */
    @RequestMapping("/addComment.do")
    public Result<?> addComment(@RequestBody Comment comment) {
        Result<?> msg = service.addComment(comment);
        return msg;
    }
    /**
     * 查询店铺评论
     *
     * @return
     */
    @RequestMapping("/selectComment.do")
    public Result<?> selectComment(int shopId) {
        Result<?> msg = service.selectComment(shopId);
        return msg;
    }

}
