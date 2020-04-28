package com.lnzz.orderservice.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.orderservice.pojo.EduOrder;
import com.lnzz.orderservice.pojo.vo.OrderQueryVo;
import com.lnzz.orderservice.service.EduOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName：OrderAdminController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/28 16:26
 * @Description
 */
@RestController
@RequestMapping("/orderservice/edu-order-admin")
@Api(value = "运营端订单管理", tags = {"运营端订单管理相关接口"})
public class OrderAdminController {

    @Autowired
    private EduOrderService orderService;

    @ApiOperation(value = "订单列表", notes = "订单列表", httpMethod = "POST")
    @PostMapping("/list/{pageNum}/{pageSize}")
    public JsonResult list(@ApiParam(name = "pageNum", value = "页数", required = true)
                           @PathVariable("pageNum") Integer pageNum,
                           @ApiParam(name = "pageSize", value = "每页显示条数", required = true)
                           @PathVariable("pageSize") Integer pageSize,
                           @RequestBody(required = false) OrderQueryVo queryVo) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }
        Page<EduOrder> memberPage = new Page<>(pageNum, pageSize);
        orderService.pageKeys(memberPage, queryVo);
        long total = memberPage.getTotal();
        List<EduOrder> rows = memberPage.getRecords();
        return JsonResult.ok().data("total", total).data("rows", rows);
    }

    @ApiOperation(value = "通过id获取订单详情", notes = "通过id获取订单详情", httpMethod = "GET")
    @GetMapping("/getOrderInfo")
    public JsonResult getOrderInfo(@ApiParam(name = "id", value = "订单id", required = true)
                                   @RequestParam("id") String id) {
        EduOrder order = orderService.getById(id);
        return JsonResult.ok().data("data", order);
    }

    @ApiOperation(value = "通过id删除订单", notes = "通过id删除订单", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JsonResult delete(@ApiParam(name = "id", value = "订单id", required = true)
                             @RequestParam("id") String id) {
        boolean result = orderService.removeById(id);
        return result ? JsonResult.ok() : JsonResult.error();
    }
}
