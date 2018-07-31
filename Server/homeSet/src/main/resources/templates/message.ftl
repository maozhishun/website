<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
</head>

<style type="text/css">
    table {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
    }

    td, th {
        font-size: 1em;
        border: 1px solid #5B4A42;
        padding: 3px 7px 2px 7px;
    }

    th {
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #24A9E1;
        color: #ffffff;
    }
</style>
<body>
<div>
    <h2>广尊科技官网今日贷款申请汇总如下：</h2>
    <table id="customers">
        <tr>
            <th>申请人</th>
            <th>贷款金额</th>
            <th>有无房产</th>
            <th>手机号码</th>
            <th>申请时间</th>
        </tr>
       
        <#if params?exists>
                <#list params as List> 
                 <tr>
                   <td>${(List.proposer)}</td>
                   <td>${(List.loanAmount)}</td>
                   <td>${(List.isHouseProperty)}</td>
                   <td>${(List.tel)}</td>
                   <td>${(List.createTime)}</td>
                  </tr>
                </#list>
            </#if>
    </table>
        <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <p>本邮件为系统自动发送，请勿回复。</p>
    <p>
******************************************************************************************<br/>
此文件仅发给指定收件人或机构。其内容可能包含某种享有法律特权或者需要保密的信息。对<br/>
于任何未经上海广尊科技公司授权而对本文件所载内容进行使用、披露、分发、变更之行为及<br/>
由此产生的后果，本公司概不承担任何责任。如您并非此文件的指定收件人或机构，请立即通<br/>
知本公司，并立即销毁此文件。谢谢合作。<br/>
******************************************************************************************<br/></p>
</div>
</body>
</html>