// ==UserScript==
 // @name         NFUAutoLogin
 // @namespace    http://tampermonkey.net/
 // @version      1.1
 // @description  Guangzhou NanFang College campus network automatic login
 // @author       CoverLi
 // @match        http://172.16.30.33/*
 // @match        http://172.16.30.45/*
 // @match        http://172.30.255.34/*
 // @grant        none
 // ==/UserScript==

(function () {
  'use strict';
  setTimeout(function () {
    setTimeout(function () {
      // 获取学号输入框并设置值
      document.getElementsByClassName("edit_lobo_cell")[1].value = "学号";
      // 获取密码输入框并设置值
      document.getElementsByClassName("edit_lobo_cell")[2].value = "密码";
      // 点击登录按钮
      document.getElementsByClassName("edit_lobo_cell")[0].click();
    }, 500);
  }, 1000);
})();
