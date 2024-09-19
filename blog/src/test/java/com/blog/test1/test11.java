package com.blog.test1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class test11 {
    public static void main(String[] args) throws Exception {
        String a= "{\"list\": [\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"2000\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040309\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"4\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"徐朝磊\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"数据管理岗k\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"2024-04-08 14:48:47\",\n" +
                "                    \"task_status\": \"成交\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"d8850a5d15234c8284a6e9cc630fc936\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"717c745a5b204e4fae5258d832a198c0\",\n" +
                "                    \"issue_time\": \"2024-04-08 14:48:47\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"166764aafe354852a95f61982db7d165\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"2\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-08 09:15:08\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"9\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"5f3983113da84f08a50fca871a812e7d\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"研发\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"1000\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040304\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"4\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"谭振祥\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"\",\n" +
                "                    \"task_status\": \"成交\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "                    \"issue_time\": \"\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"cafcd7e1aee34f2b873533b7f43ac3ce\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"1\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-03 17:59:40\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"4\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"1000\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040311\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"4\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"坐席PCY\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"数据管理岗k\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"2024-04-03 15:36:27\",\n" +
                "                    \"task_status\": \"成交\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"ee97185b074748beb7646906d40acb9b\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"717c745a5b204e4fae5258d832a198c0\",\n" +
                "                    \"issue_time\": \"2024-04-03 15:36:27\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"97403633ed4a4f39b6a9087724c81720\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"2\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-07 09:37:12\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"11\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"062b1d1690ee4311b971dbec5a956239\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"坐席\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"1\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040302\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"4\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"徐朝磊\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"数据管理岗k\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"2024-04-08 15:16:06\",\n" +
                "                    \"task_status\": \"成交\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"d8850a5d15234c8284a6e9cc630fc936\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"717c745a5b204e4fae5258d832a198c0\",\n" +
                "                    \"issue_time\": \"2024-04-08 15:16:06\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"7201ffe74fce4d2db5d0988fe0b6d174\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"3\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-07 10:51:41\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"2\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"5f3983113da84f08a50fca871a812e7d\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"研发\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"1000\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040303\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"4\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"谭振祥\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"13333330001\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"2024-04-08 15:16:14\",\n" +
                "                    \"task_status\": \"成交\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"cf64b547909a4109bd11447ed898db76\",\n" +
                "                    \"issue_time\": \"2024-04-08 15:16:14\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"ad2cdbace742453d978ac33ba89fa7d6\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"1\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-08 09:13:23\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"3\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"数据管理岗\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040301\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"1\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"\",\n" +
                "                    \"task_status\": \"新数据\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"\",\n" +
                "                    \"issue_time\": \"\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"6dd46423e28a4d439e0dc407ce6c67ff\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"13017651973\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"0\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"1\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040305\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"1\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"谭振祥\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"2024-04-08 15:16:27\",\n" +
                "                    \"task_status\": \"新数据\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"\",\n" +
                "                    \"issue_time\": \"2024-04-08 15:16:27\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"acf38ab3cfbb426da04371d4c9c8b513\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"0\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"5\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"数据管理岗\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040307\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"1\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"\",\n" +
                "                    \"task_status\": \"新数据\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"\",\n" +
                "                    \"issue_time\": \"\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"700ee2ba9f4c44d5a52c7bbf3d8b1964\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"0\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"7\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040310\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"1\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"\",\n" +
                "                    \"task_status\": \"新数据\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"\",\n" +
                "                    \"issue_time\": \"\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"c559c3a790254e409dacb61f22640c41\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"0\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"10\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040308\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"1\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"\",\n" +
                "                    \"task_status\": \"新数据\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"\",\n" +
                "                    \"issue_time\": \"\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"b15d363bfb3f409c861deef50c0a2dc5\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"0\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"8\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"max_fq_money\": \"10000.0000\",\n" +
                "                    \"followed_remarks\": \"9800\",\n" +
                "                    \"response_level_id\": \"1\",\n" +
                "                    \"create_by_id\": \"数据管理岗1\",\n" +
                "                    \"now_from_repayment_date\": \"5天15小时\",\n" +
                "                    \"age_range\": \"20岁以下\",\n" +
                "                    \"flow_model_id\": null,\n" +
                "                    \"market_activities_id\": \"\",\n" +
                "                    \"new_old_cust_id\": \"\",\n" +
                "                    \"added_services_id\": \"\",\n" +
                "                    \"middle_number\": \"\",\n" +
                "                    \"outcall_task_back_flag_id\": \"\",\n" +
                "                    \"bill_id\": \"PCY040306\",\n" +
                "                    \"layout_page_id\": \"zyzxoutcall-zyzxoutcalldetail-1710988736964detail\",\n" +
                "                    \"is_delete_id\": \"0\",\n" +
                "                    \"agentid\": \"\",\n" +
                "                    \"outcall_task_back_flag\": \"\",\n" +
                "                    \"last_call_time\": \"\",\n" +
                "                    \"task_status_id\": \"4\",\n" +
                "                    \"first_call_time\": \"\",\n" +
                "                    \"outcall_task_dis_type\": \"\",\n" +
                "                    \"seat\": \"\",\n" +
                "                    \"repayment_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"revisit_reasons\": \"\",\n" +
                "                    \"account_state_id\": \"\",\n" +
                "                    \"call_sms_temp_Id\": \"\",\n" +
                "                    \"tenant_id\": \"金融低代码\",\n" +
                "                    \"revisit_reasons_id\": \"\",\n" +
                "                    \"fm_id\": \"\",\n" +
                "                    \"gender\": \"男\",\n" +
                "                    \"limit_range\": \"\",\n" +
                "                    \"batch_id\": \"98e6df785ee54149aba305c699f09aae\",\n" +
                "                    \"now_from_bill_date\": \"5天15小时\",\n" +
                "                    \"task_id\": \"\",\n" +
                "                    \"time_since_last_follow\": \"\",\n" +
                "                    \"card_num\": \"4444\",\n" +
                "                    \"card_level\": \"\",\n" +
                "                    \"age_range_id\": \"1\",\n" +
                "                    \"remaining_due\": \"\",\n" +
                "                    \"account_state\": \"\",\n" +
                "                    \"update_by\": \"数据管理岗k\",\n" +
                "                    \"outcall_task_dis_type_id\": \"\",\n" +
                "                    \"allocate_time\": \"\",\n" +
                "                    \"task_status\": \"成交\",\n" +
                "                    \"result_type_id\": \"\",\n" +
                "                    \"contact_name\": \"\",\n" +
                "                    \"department_id\": \"\",\n" +
                "                    \"customfield00002\": \"\",\n" +
                "                    \"customfield00001\": \"\",\n" +
                "                    \"is_delete\": \"否\",\n" +
                "                    \"allow_max_outcall_count\": \"\",\n" +
                "                    \"ycwh_task_id\": \"\",\n" +
                "                    \"update_by_id\": \"717c745a5b204e4fae5258d832a198c0\",\n" +
                "                    \"issue_time\": \"\",\n" +
                "                    \"questionnaire_result_id\": \"\",\n" +
                "                    \"called_frequency_id\": \"\",\n" +
                "                    \"failed_reason\": \"\",\n" +
                "                    \"outcall_task_id\": \"\",\n" +
                "                    \"bill_date\": \"2024-04-03 00:00:00\",\n" +
                "                    \"mt_id\": \"1e2b4b80e84e41f3a330d01294e9cb79\",\n" +
                "                    \"customfield00001_id\": \"\",\n" +
                "                    \"defeat_reasons\": \"\",\n" +
                "                    \"business_key\": \"\",\n" +
                "                    \"staging_interval\": \"\",\n" +
                "                    \"revisit_time\": \"\",\n" +
                "                    \"contact_num\": \"0\",\n" +
                "                    \"phone_num\": \"17886668272\",\n" +
                "                    \"added_services\": \"\",\n" +
                "                    \"create_date\": \"2024-04-03 15:36:03\",\n" +
                "                    \"outcall_task_back_level_id\": \"\",\n" +
                "                    \"list_state\": \"\",\n" +
                "                    \"area\": \"\",\n" +
                "                    \"is_flow_type\": \"0\",\n" +
                "                    \"max_outcall_period\": \"\",\n" +
                "                    \"calls_num\": \"0\",\n" +
                "                    \"followed_num\": \"1\",\n" +
                "                    \"last_followed_time\": \"\",\n" +
                "                    \"update_date\": \"2024-04-08 09:13:11\",\n" +
                "                    \"out_call_mode\": \"\",\n" +
                "                    \"tenant_id_id\": \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"name\": \"6\",\n" +
                "                    \"cust_id\": \"\",\n" +
                "                    \"business_type_id\": \"0ec7820e78d04242bb167fee70352a03_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "                    \"pro_ins_id\": \"\",\n" +
                "                    \"defeat_reasons_id\": \"\",\n" +
                "                    \"testRemark\": \"\",\n" +
                "                    \"questionnaired_id\": \"\",\n" +
                "                    \"new_old_cust\": \"\",\n" +
                "                    \"seat_id\": \"\",\n" +
                "                    \"intention_label\": \"\",\n" +
                "                    \"frequency_rule_type\": \"0\",\n" +
                "                    \"response_level\": \"高\",\n" +
                "                    \"create_by\": \"\",\n" +
                "                    \"business_type\": \"金融营销默认业务类型\",\n" +
                "                    \"department\": \"\",\n" +
                "                    \"outcall_task_back_level\": \"\",\n" +
                "                    \"result_type\": \"\",\n" +
                "                    \"calling_number\": \"\",\n" +
                "                    \"contact_status\": \"\",\n" +
                "                    \"gender_id\": \"1\",\n" +
                "                    \"node_def_id\": \"\",\n" +
                "                    \"market_activities\": \"\",\n" +
                "                    \"category\": \"\",\n" +
                "                    \"called_frequency\": \"\",\n" +
                "                    \"remarks\": \"\"\n" +
                "                }\n" +
                "            ]}";

        JSONObject jsonObject = JSONObject.parseObject(a);
        JSONArray list = jsonObject.getJSONArray("list");
        HashMap<String, List<JSONObject>> map = new HashMap<>();
        HashMap<String, List<JSONObject>> map2 = new HashMap<>();
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject1 = list.getJSONObject(i);
            String name = jsonObject1.getString("seat_id");
            if (name==null||name.equals("")){
                continue;
            }
            if (map.containsKey(name)) {
                List<JSONObject> jsonObjects = map.get(name);
                jsonObjects.add(jsonObject1);
                map.put(name, jsonObjects);
            } else {
                List<JSONObject> jsonObjects = new ArrayList<>();
                jsonObjects.add(jsonObject1);
                map.put(name, jsonObjects);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);
    }
    public static Map<String,Object> obj2Map(Object obj) throws Exception{
        Map<String,Object> map=new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("seat_id") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }
}

class ll{
    public static void main(String[] args) {
        List<Map<String,Object>> L = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("key","123");
        map.put("value",456L);
        L.add(map);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("key","789");
        map2.put("value",258L);
        L.add(map2);
        if (L!=null){
            for (Map<String, Object> stringObjectMap : L) {
                String key = stringObjectMap.get("key").toString();
                int value = Integer.valueOf(stringObjectMap.get("value").toString());
                System.out.println(key+"--"+value);
            }
        }

        System.out.println(map.size());
        System.out.println(5);
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 减去90天，并设置时间为午夜（00:00）
        LocalDateTime tenDaysAgoMidnight = now.minusDays(0).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime nowMidnight = now.truncatedTo(ChronoUnit.DAYS);

        // 转换为ZonedDateTime（带时区）
        ZonedDateTime zonedDateTime = tenDaysAgoMidnight.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedNowDateTime = nowMidnight.atZone(ZoneId.systemDefault());

        // 转换为Instant
        Instant instantTenDaysAgoMidnight = zonedDateTime.toInstant();
        Instant instantNowMidnight = zonedNowDateTime.toInstant();

        // 获取时间戳（毫秒）
        long timestampTenDaysAgoMidnight = instantTenDaysAgoMidnight.toEpochMilli();
        long timestampNowAgoMidnight = instantNowMidnight.toEpochMilli();

        // 输出时间戳
        System.out.println("90天前整点的时间戳（毫秒）: " + timestampTenDaysAgoMidnight);
        System.out.println("当前整点的时间戳（毫秒）: " + timestampNowAgoMidnight);

    }
}
@Data
class Animal {
    private String name;
    private String pwd;
    private List<String> a;
}
@Data
class Dog extends Animal {
   private String sex;
}
class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ParseException, IOException {

//        String beginDate="{\"entId\":\"670436\",\"busiId\":\"003\",\"serialId\":\"6f013aadfb6541aebebf96748af7e6e4\",\"taskName\":\"预测外呼任务2024-04-25 11:10:45\",\"params\":{\"taskType\":\"2\",\"custTempTelNum\":\"客户号码\",\"endDate\":\"2099-12-30\",\"prefixNumList\":\"031187266738\",\"custTempList\":[\"客户号码\",\"名单ID\",\"唯一识别号\"],\"callRate\":\"1\",\"failCallCount\":\"0\",\"agentList\":\"00001067043636078,00001067043627821\",\"callInterval\":\"5\",\"startDate\":\"2024-04-25\"},\"taskId\":\"000001670436111714014664568\",\"timestamp\":1714014664815}";
//        JSONObject jsonObject = JSONObject.parseObject(beginDate);
//        System.out.println(jsonObject);
//        String url="/home/dmpsftp/upload/00000167043611171402434724520240425.txt";
//        int endIndex = url.lastIndexOf("/");
//        String filePath = endIndex < 0 ? "" : url.substring(0, endIndex );
//        String fileName = url.substring(endIndex + 1);
//        String ftpFilePath = filePath  + fileName;
//        System.out.println(fileName);
//        System.out.println(filePath);
//        System.out.println(ftpFilePath);
//        File file = new File("C:\\Users\\tzx\\Desktop\\2.txt");
//        if (!file.exists()){
//            file.createNewFile();
//        }
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        System.out.println(fileOutputStream);
//        String a="2024-05-11 09:37:05";
//        a=a.replace("-","").replace(" ","").replace(":","");
//        System.out.println(a);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a","b");
        if (!jsonObject.isEmpty()){
            System.out.println("jsonObject不为空");
        }
        if (jsonObject.isEmpty()){
            System.out.println("jsonObject为空");
        }

    }




}


class SendSZRTransFlagThread implements Runnable{
    public static BlockingQueue<JSONObject> messageQueue = new LinkedBlockingQueue<JSONObject>(10000);


    @Override
    public void run() {
        while (true) {
            JSONObject json = null;
            try {
                json = messageQueue.poll(50, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (json == null) {
                continue;
            }
            System.out.println(json);
        }
    }

    public static void addMsg(JSONObject json) {
        messageQueue.offer(json);
    }
}
class Test1 {

}