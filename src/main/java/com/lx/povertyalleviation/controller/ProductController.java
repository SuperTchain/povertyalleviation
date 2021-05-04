package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.pojo.User;
import com.lx.povertyalleviation.service.ProductService;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/31 15:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/product")
@Api(tags = "产品模块")
public class ProductController {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(ProductController.class);
    /**
     * 引入服务
     */
    @Autowired
    private ProductService productService;

    /**
     * 管理员跳转到产品列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toProductList")
    @ApiOperation(value = "跳转到产品列表界面")
    public String toProductList() {
        return "product/productList";
    }


    /**
     * 商家跳转到产品列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toProductListOfSales")
    @ApiOperation(value = "跳转到产品列表界面")
    public String toProductListOfSales() {
        return "product/productListOfSales";
    }


    /**
     * 管理员跳转到添加产品列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toAddProductByAdmin")
    @ApiOperation(value = "管理员跳转到添加产品界面")
    public String toAddProductByAdmin() {
        return "product/addProductByAdmin";
    }

    /**
     * 商家跳转到添加产品列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toAddProduct")
    @ApiOperation(value = "商家跳转到添加产品界面")
    public String toAddProduct() {
        return "product/addProduct";
    }

    /**
     * 跳转到查看产品界面
     * @return 界面
     */
    @GetMapping("/toViewProduct")
    @ApiOperation(value = "跳转到查看产品界面")
    public String toViewProduct(HttpServletRequest request){
        String productId = request.getParameter("id");
        HttpSession session = request.getSession();
        session.setAttribute("productId",productId);
        return "product/viewProduct";
    }

    /**
     * 跳转到查看产品界面(登陆后)
     * @return 界面
     */
    @GetMapping("/toViewProductAfterLogin")
    @ApiOperation(value = "跳转到查看产品界面（登录后）")
    public String toViewProductAfterLogin(HttpServletRequest request){
//        String productId = request.getParameter("id");
//        HttpSession session = request.getSession();
//        session.setAttribute("productId",productId);
        return "product/viewProductAfterLogin";
    }


    @GetMapping("/toEditProduct")
    @ApiOperation(value = "跳转到编辑界面")
    public String toEditProduct(){
        return "product/editProduct";
    }

    @GetMapping("/toHotSaleProduct")
    @ApiOperation(value = "跳转到热销商品界面")
    public String toHotSaleProduct(){
        return "product/hotSaleProduct";
    }

    @GetMapping("/toFindProductByKind1")
    @ApiOperation(value = "跳转到产品分类-水果界面")
    public String toFindProductByKind1(){
        return "product/fruitProduct";
    }


    @GetMapping("/toFindProductByKind2")
    @ApiOperation(value = "跳转到产品分类-农产品界面")
    public String toFindProductByKind2(){
        return "product/farmProduct";
    }

    @GetMapping("/toFindProductByKind3")
    @ApiOperation(value = "跳转到产品分类-家畜界面")
    public String toFindProductByKind3(){
        return "product/animalProduct";
    }

    @GetMapping("/toIntroduceProduct")
    @ApiOperation(value = "跳转到产品介绍界面")
    public String  toIntroduceProduct(){
        return "product/introduceProduct";
    }

    /**
     * 查询所有产品信息
     *
     * @param page  页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/findAllProduct")
    @ResponseBody
    @ApiOperation(value = "查询所有产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询所有产品信息",url = "/product/findAllProduct")
    public Result findAllProduct(Integer page, Integer limit) {
        Result result = productService.findAllProduct(page, limit);
        return result;
    }


    /**
     * 查询产品的营养信息
     *
     * @param page  页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @PostMapping("/findCompositionList")
    @ResponseBody
    @ApiOperation(value = "查询产品的营养信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询产品的营养信息",url = "/product/findCompositionList")
    public Result findCompositionList(Integer page, Integer limit) {
        Result result = productService.findCompositionList(page, limit);
        return result;
    }

    /**
     * 根据传入条件查询产品信息
     *
     * @param productId   产品ID
     * @param productName 产品名称
     * @param productKind   产品类别
     * @param page        页数
     * @param limit       每页条数
     * @return 封装结果
     */
    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "根据条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品ID", dataType = "Integer"),
            @ApiImplicitParam(name = "productName", value = "产品名称", dataType = "String"),
            @ApiImplicitParam(name = "productKind", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "根据传入条件查询产品信息",url = "/product/search")
    public Result serachProduct(Integer productId, String productName, String productKind, Integer page, Integer limit) {
        Result result = productService.search(productId, productName, productKind, page, limit);
        logger.info("产品条件搜索查询成功");
        return result;
    }


    /**
     * 商家添加产品
     * @param product 产品实体类
     * @return 结果
     */
    @PostMapping("/addProduct")
    @ResponseBody
    @ApiOperation(value = "商家添加产品")
    @RecordOperation(name = "商家添加产品",url = "/product/addProduct")
    public Result addProduct(@ApiParam(name = "product", value = "产品实体类") Product product,HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        Result result = productService.addProduct(product,userId);
        logger.info("成功添加产品");
        result.setStatus(200);
        return result;
    }


    /**
     * 管理员添加产品
     * @param product 产品实体类
     * @return 结果
     */
    @PostMapping("/addProductByAdmin")
    @ResponseBody
    @ApiOperation(value = "管理员添加产品")
    @RecordOperation(name = "管理员添加产品",url = "/product/addProduct")
    public Result addProductByAdmin(@ApiParam(name = "product", value = "产品实体类") Product product,HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Result result = productService.addProduct(product,userId);
        logger.info("成功添加产品");
        result.setStatus(200);
        return result;
    }

    /**
     * 根据Id查询产品信息
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("/findProductById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询产品")
    @RecordOperation(name = "根据Id查询产品信息",url = "/product/findProductById")
    public Result findProductById(@ApiParam(name = "id", value = "产品Id") Integer id) {
        Result result = productService.findProductById(id);
        logger.info("根据产品ID查询成功");
        return result;
    }

    /**
     * 根据id查询所有产品信息
     *
     * @param page  页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/findProductListById")
    @ResponseBody
    @ApiOperation(value = "根据id查询所有产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询所有产品信息",url = "/product/findProductListById")
    public Result findProductListById(HttpSession session,Integer page, Integer limit) {
        Integer userId = (Integer) session.getAttribute("userId");
        Result result = productService.findProductListById(userId,page, limit);
        return result;
    }

    /**
     * 删除产品
     *
     * @param id 产品id
     * @return 封装结果集
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value = "删除产品")
    @RecordOperation(name="删除产品",url = "/product/deleteById")
    public Result deleteById(@ApiParam(name = "id", value = "产品Id") Integer id) {
        Result result = productService.deleteById(id);
        logger.info("成功删除产品");
        return result;
    }

    /**
     * 批量删除
     *
     * @param ids 删除id数组
     * @return 封装结果集
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    @ApiOperation(value = "批量删除产品")
    @RecordOperation(name = "批量删除产品",url = "/product/batchDelete")
    public Result batchDeleteByProductId(@ApiParam(name = "ids", value = "产品名数组") String[] ids) {
        System.out.println(ids);
        Result result = productService.batchDeleteByProductId(ids);
        logger.info("成功批量删除产品");
        result.setStatus(200);
        result.setItem("批量删除成功");
        return result;
    }


    /**
     * 更新用户信息
     *
     * @param product 用户信息
     * @return 封装结果
     */
    @PostMapping("/updateProduct")
    @ResponseBody
    @ApiOperation(value = "更新产品信息")
    @RecordOperation(name = "更新产品信息",url = "/product/updateProduct")
    public Result updateProduct(@ApiParam(name = "product", value = "产品实体类") Product product) {
        Result result = productService.updateProduct(product);
        logger.info("更新产品成功" + result);
        return result;
    }




    @PostMapping("/findProductByCondition")
    @ResponseBody
    @ApiOperation(value = "根据条件查找产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productKind", value = "产品类别", dataType = "Stringt"),
            @ApiImplicitParam(name = "hotSaleStatus", value = "热销状态", dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "根据条件查找产品信息",url = "/product/findProductByCondition")
    public Result findProductByCondition(String productKind,Integer hotSaleStatus, Integer page, Integer limit) {
        Result result = productService.findProductByCondition(productKind,hotSaleStatus,page,limit);
        logger.info("根据条件查找产品成功" + result);
        return result;
    }



    @PostMapping("/upload")
    @ResponseBody
    public Result uploadFile(@RequestParam("file") MultipartFile uploadFile) throws FileNotFoundException {
        Result result = new Result();

        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path+"/static/img/products");
        if (!uploadDir.exists()) {
            System.out.println("上传路径不存在，正在创建...");
            uploadDir.mkdir();
        }
        if ( uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            //生成随机数作为文件名
            String hash = Integer.toHexString(new Random().nextInt());
            String realName=hash+oldName;
            System.out.println("[上传的文件名]：" + realName);
            //我的文件保存在static目录下的products
            File productImgUrl = new File(path + "/static/img/products" , realName);
            try {
                //保存图片
                uploadFile.transferTo(productImgUrl);
                //返回成功结果，附带文件的相对路径
                result.setStatus(200);
                result.setMessage("上传成功");
                result.setItem(realName);
                return result;
            }catch (IOException e) {
                e.printStackTrace();
                result.setStatus(400);
                result.setMessage("上传失败");
                return result;
            }
        }else {
            System.out.println("上传的文件为空");
            result.setStatus(300);
            result.setMessage("上传的文件为空");
            return result;
        }
    }


    /**
     * 根据产品名模糊查询所有产品信息
     *
     * @param page  页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/findProductLikeName")
    @ResponseBody
    @ApiOperation(value = "根据产品名模糊查询所有产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productName",value = "产品名称"),
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "根据产品名模糊查询所有产品信息",url = "/product/findProductLikeName")
    public Result findProductLikeName(String productName,Integer page, Integer limit) {
        Result result = productService.findProductLikeName(productName,page, limit);
        return result;
    }

}
