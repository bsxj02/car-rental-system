package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.QichexinxiEntity;
import com.entity.view.QichexinxiView;

import com.service.QichexinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 汽车信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-01 18:29:45
 */
@RestController
@RequestMapping("/qichexinxi")
public class QichexinxiController {
    @Autowired
    private QichexinxiService qichexinxiService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QichexinxiEntity qichexinxi,
		HttpServletRequest request){
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();

		PageUtils page = qichexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qichexinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QichexinxiEntity qichexinxi, 
		HttpServletRequest request){
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();

		PageUtils page = qichexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qichexinxi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QichexinxiEntity qichexinxi){
       	EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qichexinxi, "qichexinxi")); 
        return R.ok().put("data", qichexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QichexinxiEntity qichexinxi){
        EntityWrapper< QichexinxiEntity> ew = new EntityWrapper< QichexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qichexinxi, "qichexinxi")); 
		QichexinxiView qichexinxiView =  qichexinxiService.selectView(ew);
		return R.ok("查询汽车信息成功").put("data", qichexinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QichexinxiEntity qichexinxi = qichexinxiService.selectById(id);
		qichexinxi.setClicknum(qichexinxi.getClicknum()+1);
		qichexinxi.setClicktime(new Date());
		qichexinxiService.updateById(qichexinxi);
        qichexinxi = qichexinxiService.selectView(new EntityWrapper<QichexinxiEntity>().eq("id", id));
        return R.ok().put("data", qichexinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QichexinxiEntity qichexinxi = qichexinxiService.selectById(id);
		qichexinxi.setClicknum(qichexinxi.getClicknum()+1);
		qichexinxi.setClicktime(new Date());
		qichexinxiService.updateById(qichexinxi);
        qichexinxi = qichexinxiService.selectView(new EntityWrapper<QichexinxiEntity>().eq("id", id));
        return R.ok().put("data", qichexinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QichexinxiEntity qichexinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qichexinxi);
        qichexinxiService.insert(qichexinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QichexinxiEntity qichexinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qichexinxi);
        qichexinxiService.insert(qichexinxi);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QichexinxiEntity qichexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qichexinxi);
        qichexinxiService.updateById(qichexinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qichexinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,QichexinxiEntity qichexinxi, HttpServletRequest request,String pre){
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = qichexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qichexinxi), params), params));
        return R.ok().put("data", page);
    }






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        List<Map<String, Object>> result = qichexinxiService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = qichexinxiService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        List<Map<String, Object>> result = qichexinxiService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = qichexinxiService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        List<Map<String, Object>> result = qichexinxiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }


    /**
     * 分组统计
     */
    @RequestMapping("/typeStat/qichezhuangtai/qichemingcheng")
    @IgnoreAuth
    public R qichemingchengqichezhuangtaiTypeStat(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        List<Map<String, Object>> result = qichexinxiService.qichemingchengqichezhuangtaiTypeStat(params, ew);
        return R.ok().put("data", result);
    }


    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,QichexinxiEntity qichexinxi, HttpServletRequest request){
        EntityWrapper<QichexinxiEntity> ew = new EntityWrapper<QichexinxiEntity>();
        int count = qichexinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qichexinxi), params), params));
        return R.ok().put("data", count);
    }



}
