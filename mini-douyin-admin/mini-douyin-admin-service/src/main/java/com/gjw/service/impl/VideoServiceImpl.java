package com.gjw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjw.enums.BGMOperatorTypeEnum;
import com.gjw.mapper.BgmMapper;
import com.gjw.pojo.Bgm;
import com.gjw.pojo.BgmExample;
import com.gjw.service.VideoService;
import com.gjw.utils.JsonUtils;
import com.gjw.utils.PagedResult;
import com.gjw.web.util.ZKCurator;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/22.
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private BgmMapper bgmMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private ZKCurator zkCurator;


    @Override
    public PagedResult queryBgmList(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        BgmExample example = new BgmExample();
        List<Bgm> list = bgmMapper.selectByExample(example);

        PageInfo<Bgm> pageList = new PageInfo<>(list);

        // 改变分页的显示结果
        PagedResult result = new PagedResult();
        result.setTotal(pageList.getPages());
        result.setRows(list);
        result.setPage(page);
        result.setRecords(pageList.getTotal());

        return result;
    }

    @Override
    public void addBgm(Bgm bgm) {
        String bgmId = sid.nextShort();
        bgm.setId(bgmId);
        bgmMapper.insert(bgm);

        Map<String, String> map = new HashMap<>();
        map.put("operType", BGMOperatorTypeEnum.ADD.type);
        map.put("path", bgm.getPath());

        // 使用zookeeper存入bgm
        zkCurator.sendBgmOperator(bgmId, JsonUtils.objectToJson(map));
    }

    @Override
    public void deleteBgm(String id) {
        Bgm bgm = bgmMapper.selectByPrimaryKey(id);

        bgmMapper.deleteByPrimaryKey(id);

        Map<String, String> map = new HashMap<>();
        map.put("operType", BGMOperatorTypeEnum.DELETE.type);
        map.put("path", bgm.getPath());

        zkCurator.sendBgmOperator(id, JsonUtils.objectToJson(map));
    }
}
