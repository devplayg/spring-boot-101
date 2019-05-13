package com.devplayg.vms.controller;

import com.devplayg.vms.vo.EnumMapper;
import com.devplayg.vms.vo.EnumValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("code")
@RestController
public class CodeController {
    private EnumMapper enumMapper;

    public CodeController(EnumMapper enumMapper) {
        this.enumMapper = enumMapper;
    }

    @GetMapping("{key}")
    public Map<String, List<EnumValue>> getMapper(@PathVariable String key) {
        return enumMapper.get(key);
    }
}
