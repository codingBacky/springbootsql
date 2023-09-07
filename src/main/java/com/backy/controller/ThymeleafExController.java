package com.backy.controller;

import com.backy.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {
    @GetMapping(value = "/ex01")
    public void thymeleafExemple01(Model model){
        model.addAttribute("data", "thymeleaf Exam");
                //return "thymeleaf/Ex01";
        //String으로 리턴하고 리턴하는 값은 요청 전체 경로 =>/thymeleaf/ex01
    }
    @GetMapping(value = "/ex02")
    public void thymthymeleafExemple02(Model model){
        ItemDto dto = new ItemDto();
        dto.setPrice(10000);
        dto.setItemDetail("상품 상세 설명");
        dto.setItemNm("테스트 상품1");
        dto.setRegTime(LocalDateTime.now());
        model.addAttribute("itemDto", dto);
    }

    @GetMapping(value = {"/ex03","/ex04"})
    public void  thymthymeleafExemple03(Model model){
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(int i = 1; i < 11; i++){
            ItemDto dto = new ItemDto();
            dto.setPrice(10000 * i);
            dto.setItemNm("테스트 상품" + i);
            dto.setRegTime(LocalDateTime.now());
            dto.setItemDetail("상품 상세 설명" + i);
            itemDtoList.add(dto);
        }
        model.addAttribute("itemDtoList", itemDtoList);
    }
    @GetMapping(value = {"/ex05"})
    public void  thymthymeleafExemple05(Model model){
    }
    @GetMapping(value = {"/ex06"})
    public void  thymthymeleafExemple06(@ModelAttribute("param1") String param1, @ModelAttribute("param2") String param2, Model model){
//        model.addAttribute("param1", param1);
//        model.addAttribute("param2", param2);
    }
    @GetMapping(value = {"/ex07"})
    public void  thymthymeleafExemple07(){
    }
}
