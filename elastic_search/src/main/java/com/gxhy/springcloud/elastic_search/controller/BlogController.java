package com.gxhy.springcloud.elastic_search.controller;

import com.gxhy.springcloud.elastic_search.Model.BlogModel;
import com.gxhy.springcloud.elastic_search.Model.Result;
import com.gxhy.springcloud.elastic_search.respository.BlogRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @PostMapping(value = "/add")
    public Result add(@RequestBody BlogModel blogModel) {
        Result result = new Result();
        blogRepository.save(blogModel);
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    @GetMapping(value = "/get/{id}")
    public Result getById(@PathVariable String id) {
        Result result = new Result();
        if (StringUtils.isEmpty(id)) {
            result.setMsg("id不能为空");
            return result;
        }
        Optional<BlogModel> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            BlogModel blogModel = blogModelOptional.get();
            result.setData(blogModel);
            result.setCode(200);
            return result;
        }
        result.setMsg("查询错误");
        result.setCode(500);
        return result;
    }

    @GetMapping(value = "/getAll")
    public Result getAll() {
        Result result = new Result();
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        result.setCode(200);
        result.setMsg("查询成功");
        result.setData(list);
        return result;

    }

    @DeleteMapping(value = "/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        Result result = new Result();
        if (StringUtils.isEmpty(id)) {
            result.setMsg("id不能为空");
            result.setCode(500);
            return result;
        }
        blogRepository.deleteById(id);
        result.setMsg("删除成功");
        result.setCode(200);
        return result;

    }

    @DeleteMapping(value = "/deleteAll")
    public Result deleteAll() {
        Result result = new Result();
        blogRepository.deleteAll();
        result.setCode(200);
        result.setMsg("删除成功");
        return result;
    }

    @GetMapping("/rep/search")
    public Result repSearchTitle(String keyword) {
        Result result = new Result();
        if (StringUtils.isEmpty(keyword)) {
            result.setMsg("请输入检索关键字");
            result.setCode(200);
            return result;
        }
        List<BlogModel> list = blogRepository.findByTitleContains(keyword);
        result.setCode(200);
        result.setMsg("检索成功");
        result.setData(list);
        return result;
    }
}
