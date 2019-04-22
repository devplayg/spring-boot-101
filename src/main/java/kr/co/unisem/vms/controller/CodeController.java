package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.vo.EnumMapper;
import kr.co.unisem.vms.vo.EnumValue;
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
