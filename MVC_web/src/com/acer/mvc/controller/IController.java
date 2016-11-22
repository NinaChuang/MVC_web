package com.acer.mvc.controller;

import java.util.List;
import java.util.Map;

import com.acer.mvc.vo.UserVo;

public interface IController {
  
  public List<Map<String, String>> getFilterTowns(String provinceNo , String cityNo);
  public List<Map<String, String>> getListMapCity();
  public Map<String, String> doSubmit(UserVo vo);

}
