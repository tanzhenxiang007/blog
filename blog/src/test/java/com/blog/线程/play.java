package com.blog.线程;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.玩玩.NFS.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class play {
    public static void main(String[] args) {
        String j=" {\n" +
                "    \"total\" : 7,\n" +
                "    \"max_score\" : 4.6051702,\n" +
                "    \"hits\" : [\n" +
                "      {\n" +
                "        \"_index\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_type\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_id\" : \"jzRmMY4B8P4kPmIv5K2H\",\n" +
                "        \"_score\" : 4.6051702,\n" +
                "        \"_source\" : {\n" +
                "          \"followId\" : null,\n" +
                "          \"bill_id\" : \"tzx2024030121\",\n" +
                "          \"batch_id\" : \"5ee971b243ab400fb9383954053fdfde\",\n" +
                "          \"dept_name\" : \"数据管理岗\",\n" +
                "          \"dept_id\" : \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "          \"call_record_id\" : \"S20240312144246744973C0A8203302520146\",\n" +
                "          \"belong_seat\" : null,\n" +
                "          \"type\" : \"1\",\n" +
                "          \"task_status\" : null,\n" +
                "          \"order_id\" : null,\n" +
                "          \"follow_person\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"tenant_id\" : \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "          \"is_delete\" : \"0\",\n" +
                "          \"follow_time\" : \"2024-03-12 14:43:30\",\n" +
                "          \"create_time\" : \"2024-03-12 14:43:30\",\n" +
                "          \"follow_reason\" : null,\n" +
                "          \"remark\" : null,\n" +
                "          \"znwh_record_file\" : null,\n" +
                "          \"znwh_task_name\" : null,\n" +
                "          \"outcall_task_back_level\" : null,\n" +
                "          \"send_result\" : null,\n" +
                "          \"text_serialId\" : null,\n" +
                "          \"text_templet\" : null,\n" +
                "          \"text_sendResult\" : null,\n" +
                "          \"qcCreateTime\" : null,\n" +
                "          \"newestQcCreateTime\" : null,\n" +
                "          \"qaScore\" : null,\n" +
                "          \"newestQcScore\" : null,\n" +
                "          \"qcIsPass\" : null,\n" +
                "          \"newestQcIsPass\" : null,\n" +
                "          \"qaState\" : null,\n" +
                "          \"qcAgentId\" : null,\n" +
                "          \"newestQcAgentId\" : null,\n" +
                "          \"qcRemark\" : null,\n" +
                "          \"resultId\" : null,\n" +
                "          \"templateId\" : null,\n" +
                "          \"is_repeat\" : null,\n" +
                "          \"call_result\" : 2,\n" +
                "          \"call_result_desc\" : null,\n" +
                "          \"call_id\" : null,\n" +
                "          \"contact_time\" : null,\n" +
                "          \"recordname\" : null,\n" +
                "          \"call_show_flag\" : null,\n" +
                "          \"record_name\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_type\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_id\" : \"hzSgMI4B8P4kPmIvya1J\",\n" +
                "        \"_score\" : 4.564348,\n" +
                "        \"_source\" : {\n" +
                "          \"followId\" : null,\n" +
                "          \"bill_id\" : \"tzx2024030129\",\n" +
                "          \"batch_id\" : \"5ee971b243ab400fb9383954053fdfde\",\n" +
                "          \"dept_name\" : \"数据管理岗\",\n" +
                "          \"dept_id\" : \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "          \"call_record_id\" : \"S20240312110629688090C0A8203303169634\",\n" +
                "          \"belong_seat\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"type\" : \"2\",\n" +
                "          \"task_status\" : \"3\",\n" +
                "          \"order_id\" : null,\n" +
                "          \"follow_person\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"tenant_id\" : \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "          \"is_delete\" : \"0\",\n" +
                "          \"follow_time\" : \"2024-03-12 11:07:07\",\n" +
                "          \"create_time\" : \"2024-03-12 11:07:07\",\n" +
                "          \"follow_reason\" : \"2\",\n" +
                "          \"remark\" : \"1\",\n" +
                "          \"znwh_record_file\" : null,\n" +
                "          \"znwh_task_name\" : null,\n" +
                "          \"outcall_task_back_level\" : null,\n" +
                "          \"send_result\" : null,\n" +
                "          \"text_serialId\" : null,\n" +
                "          \"text_templet\" : null,\n" +
                "          \"text_sendResult\" : null,\n" +
                "          \"qcCreateTime\" : null,\n" +
                "          \"newestQcCreateTime\" : null,\n" +
                "          \"qaScore\" : null,\n" +
                "          \"newestQcScore\" : null,\n" +
                "          \"qcIsPass\" : null,\n" +
                "          \"newestQcIsPass\" : null,\n" +
                "          \"qaState\" : null,\n" +
                "          \"qcAgentId\" : null,\n" +
                "          \"newestQcAgentId\" : null,\n" +
                "          \"qcRemark\" : null,\n" +
                "          \"resultId\" : null,\n" +
                "          \"templateId\" : null,\n" +
                "          \"is_repeat\" : null,\n" +
                "          \"call_result\" : null,\n" +
                "          \"call_result_desc\" : null,\n" +
                "          \"call_id\" : null,\n" +
                "          \"contact_time\" : null,\n" +
                "          \"recordname\" : null,\n" +
                "          \"call_show_flag\" : null,\n" +
                "          \"record_name\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_type\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_id\" : \"hjSgMI4B8P4kPmIvZK0C\",\n" +
                "        \"_score\" : 4.4620695,\n" +
                "        \"_source\" : {\n" +
                "          \"followId\" : null,\n" +
                "          \"bill_id\" : \"tzx2024030129\",\n" +
                "          \"batch_id\" : \"5ee971b243ab400fb9383954053fdfde\",\n" +
                "          \"dept_name\" : \"数据管理岗\",\n" +
                "          \"dept_id\" : \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "          \"call_record_id\" : \"S20240312110629688090C0A8203303169634\",\n" +
                "          \"belong_seat\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"type\" : \"8\",\n" +
                "          \"task_status\" : null,\n" +
                "          \"order_id\" : null,\n" +
                "          \"follow_person\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"tenant_id\" : \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "          \"is_delete\" : \"0\",\n" +
                "          \"follow_time\" : \"2024-03-12 11:06:41\",\n" +
                "          \"create_time\" : \"2024-03-12 11:06:41\",\n" +
                "          \"follow_reason\" : null,\n" +
                "          \"remark\" : null,\n" +
                "          \"znwh_record_file\" : null,\n" +
                "          \"znwh_task_name\" : null,\n" +
                "          \"outcall_task_back_level\" : null,\n" +
                "          \"send_result\" : null,\n" +
                "          \"text_serialId\" : null,\n" +
                "          \"text_templet\" : null,\n" +
                "          \"text_sendResult\" : null,\n" +
                "          \"qcCreateTime\" : null,\n" +
                "          \"newestQcCreateTime\" : null,\n" +
                "          \"qaScore\" : null,\n" +
                "          \"newestQcScore\" : null,\n" +
                "          \"qcIsPass\" : null,\n" +
                "          \"newestQcIsPass\" : null,\n" +
                "          \"qaState\" : null,\n" +
                "          \"qcAgentId\" : null,\n" +
                "          \"newestQcAgentId\" : null,\n" +
                "          \"qcRemark\" : null,\n" +
                "          \"resultId\" : null,\n" +
                "          \"templateId\" : null,\n" +
                "          \"is_repeat\" : null,\n" +
                "          \"call_result\" : 1,\n" +
                "          \"call_result_desc\" : null,\n" +
                "          \"call_id\" : null,\n" +
                "          \"contact_time\" : null,\n" +
                "          \"recordname\" : null,\n" +
                "          \"call_show_flag\" : null,\n" +
                "          \"record_name\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_type\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_id\" : \"iTSlMI4B8P4kPmIvx61K\",\n" +
                "        \"_score\" : 4.0876555,\n" +
                "        \"_source\" : {\n" +
                "          \"followId\" : null,\n" +
                "          \"bill_id\" : \"tzx2024030121\",\n" +
                "          \"batch_id\" : \"5ee971b243ab400fb9383954053fdfde\",\n" +
                "          \"dept_name\" : null,\n" +
                "          \"dept_id\" : null,\n" +
                "          \"call_record_id\" : null,\n" +
                "          \"belong_seat\" : null,\n" +
                "          \"type\" : \"8\",\n" +
                "          \"task_status\" : null,\n" +
                "          \"order_id\" : null,\n" +
                "          \"follow_person\" : \"ycwh\",\n" +
                "          \"tenant_id\" : \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "          \"is_delete\" : \"0\",\n" +
                "          \"follow_time\" : \"2024-03-12 11:12:34\",\n" +
                "          \"create_time\" : \"2024-03-12 11:12:34\",\n" +
                "          \"follow_reason\" : \"预测外呼任务呼叫未接通\",\n" +
                "          \"remark\" : null,\n" +
                "          \"znwh_record_file\" : null,\n" +
                "          \"znwh_task_name\" : null,\n" +
                "          \"outcall_task_back_level\" : null,\n" +
                "          \"send_result\" : null,\n" +
                "          \"text_serialId\" : null,\n" +
                "          \"text_templet\" : null,\n" +
                "          \"text_sendResult\" : null,\n" +
                "          \"qcCreateTime\" : null,\n" +
                "          \"newestQcCreateTime\" : null,\n" +
                "          \"qaScore\" : null,\n" +
                "          \"newestQcScore\" : null,\n" +
                "          \"qcIsPass\" : null,\n" +
                "          \"newestQcIsPass\" : null,\n" +
                "          \"qaState\" : null,\n" +
                "          \"qcAgentId\" : null,\n" +
                "          \"newestQcAgentId\" : null,\n" +
                "          \"qcRemark\" : null,\n" +
                "          \"resultId\" : null,\n" +
                "          \"templateId\" : null,\n" +
                "          \"is_repeat\" : null,\n" +
                "          \"call_result\" : 2,\n" +
                "          \"call_result_desc\" : null,\n" +
                "          \"call_id\" : null,\n" +
                "          \"contact_time\" : \"2024-03-12 11:12:34\",\n" +
                "          \"recordname\" : null,\n" +
                "          \"call_show_flag\" : null,\n" +
                "          \"record_name\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_type\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_id\" : \"jjRmMY4B8P4kPmIvFa1s\",\n" +
                "        \"_score\" : 4.0876555,\n" +
                "        \"_source\" : {\n" +
                "          \"followId\" : null,\n" +
                "          \"bill_id\" : \"tzx2024030121\",\n" +
                "          \"batch_id\" : \"5ee971b243ab400fb9383954053fdfde\",\n" +
                "          \"dept_name\" : \"数据管理岗\",\n" +
                "          \"dept_id\" : \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "          \"call_record_id\" : \"S20240312144215804678C0A8203301509059\",\n" +
                "          \"belong_seat\" : null,\n" +
                "          \"type\" : \"1\",\n" +
                "          \"task_status\" : null,\n" +
                "          \"order_id\" : null,\n" +
                "          \"follow_person\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"tenant_id\" : \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "          \"is_delete\" : \"0\",\n" +
                "          \"follow_time\" : \"2024-03-12 14:42:37\",\n" +
                "          \"create_time\" : \"2024-03-12 14:42:37\",\n" +
                "          \"follow_reason\" : null,\n" +
                "          \"remark\" : null,\n" +
                "          \"znwh_record_file\" : null,\n" +
                "          \"znwh_task_name\" : null,\n" +
                "          \"outcall_task_back_level\" : null,\n" +
                "          \"send_result\" : null,\n" +
                "          \"text_serialId\" : null,\n" +
                "          \"text_templet\" : null,\n" +
                "          \"text_sendResult\" : null,\n" +
                "          \"qcCreateTime\" : null,\n" +
                "          \"newestQcCreateTime\" : null,\n" +
                "          \"qaScore\" : null,\n" +
                "          \"newestQcScore\" : null,\n" +
                "          \"qcIsPass\" : null,\n" +
                "          \"newestQcIsPass\" : null,\n" +
                "          \"qaState\" : null,\n" +
                "          \"qcAgentId\" : null,\n" +
                "          \"newestQcAgentId\" : null,\n" +
                "          \"qcRemark\" : null,\n" +
                "          \"resultId\" : null,\n" +
                "          \"templateId\" : null,\n" +
                "          \"is_repeat\" : null,\n" +
                "          \"call_result\" : 1,\n" +
                "          \"call_result_desc\" : null,\n" +
                "          \"call_id\" : null,\n" +
                "          \"contact_time\" : null,\n" +
                "          \"recordname\" : null,\n" +
                "          \"call_show_flag\" : null,\n" +
                "          \"record_name\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_type\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_id\" : \"jDRLMY4B8P4kPmIv0q2n\",\n" +
                "        \"_score\" : 3.9665112,\n" +
                "        \"_source\" : {\n" +
                "          \"followId\" : null,\n" +
                "          \"bill_id\" : \"tzx2024030129\",\n" +
                "          \"batch_id\" : \"5ee971b243ab400fb9383954053fdfde\",\n" +
                "          \"dept_name\" : \"数据管理岗\",\n" +
                "          \"dept_id\" : \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "          \"call_record_id\" : \"S20240312141332891393C0A8203301486548\",\n" +
                "          \"belong_seat\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"type\" : \"1\",\n" +
                "          \"task_status\" : null,\n" +
                "          \"order_id\" : null,\n" +
                "          \"follow_person\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"tenant_id\" : \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "          \"is_delete\" : \"0\",\n" +
                "          \"follow_time\" : \"2024-03-12 14:13:56\",\n" +
                "          \"create_time\" : \"2024-03-12 14:13:56\",\n" +
                "          \"follow_reason\" : null,\n" +
                "          \"remark\" : null,\n" +
                "          \"znwh_record_file\" : null,\n" +
                "          \"znwh_task_name\" : null,\n" +
                "          \"outcall_task_back_level\" : null,\n" +
                "          \"send_result\" : null,\n" +
                "          \"text_serialId\" : null,\n" +
                "          \"text_templet\" : null,\n" +
                "          \"text_sendResult\" : null,\n" +
                "          \"qcCreateTime\" : null,\n" +
                "          \"newestQcCreateTime\" : null,\n" +
                "          \"qaScore\" : null,\n" +
                "          \"newestQcScore\" : null,\n" +
                "          \"qcIsPass\" : null,\n" +
                "          \"newestQcIsPass\" : null,\n" +
                "          \"qaState\" : null,\n" +
                "          \"qcAgentId\" : null,\n" +
                "          \"newestQcAgentId\" : null,\n" +
                "          \"qcRemark\" : null,\n" +
                "          \"resultId\" : null,\n" +
                "          \"templateId\" : null,\n" +
                "          \"is_repeat\" : null,\n" +
                "          \"call_result\" : 1,\n" +
                "          \"call_result_desc\" : null,\n" +
                "          \"call_id\" : null,\n" +
                "          \"contact_time\" : null,\n" +
                "          \"recordname\" : null,\n" +
                "          \"call_show_flag\" : null,\n" +
                "          \"record_name\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_type\" : \"dmp_sccc_finan_follow_f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "        \"_id\" : \"iDSjMI4B8P4kPmIvGa3F\",\n" +
                "        \"_score\" : 3.9665112,\n" +
                "        \"_source\" : {\n" +
                "          \"followId\" : null,\n" +
                "          \"bill_id\" : \"tzx2024030129\",\n" +
                "          \"batch_id\" : \"5ee971b243ab400fb9383954053fdfde\",\n" +
                "          \"dept_name\" : \"数据管理岗\",\n" +
                "          \"dept_id\" : \"9aef9573440544b79ff5a3f424c1e83d\",\n" +
                "          \"call_record_id\" : \"S20240312110854966152C0A8203300308621\",\n" +
                "          \"belong_seat\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"type\" : \"1\",\n" +
                "          \"task_status\" : null,\n" +
                "          \"order_id\" : null,\n" +
                "          \"follow_person\" : \"1a05871e56c24baeba281e616bfdb85c\",\n" +
                "          \"tenant_id\" : \"f6192b0f69c949c2acd5b1a791dd5bc7\",\n" +
                "          \"is_delete\" : \"0\",\n" +
                "          \"follow_time\" : \"2024-03-12 11:09:39\",\n" +
                "          \"create_time\" : \"2024-03-12 11:09:39\",\n" +
                "          \"follow_reason\" : null,\n" +
                "          \"remark\" : null,\n" +
                "          \"znwh_record_file\" : null,\n" +
                "          \"znwh_task_name\" : null,\n" +
                "          \"outcall_task_back_level\" : null,\n" +
                "          \"send_result\" : null,\n" +
                "          \"text_serialId\" : null,\n" +
                "          \"text_templet\" : null,\n" +
                "          \"text_sendResult\" : null,\n" +
                "          \"qcCreateTime\" : null,\n" +
                "          \"newestQcCreateTime\" : null,\n" +
                "          \"qaScore\" : null,\n" +
                "          \"newestQcScore\" : null,\n" +
                "          \"qcIsPass\" : null,\n" +
                "          \"newestQcIsPass\" : null,\n" +
                "          \"qaState\" : null,\n" +
                "          \"qcAgentId\" : null,\n" +
                "          \"newestQcAgentId\" : null,\n" +
                "          \"qcRemark\" : null,\n" +
                "          \"resultId\" : null,\n" +
                "          \"templateId\" : null,\n" +
                "          \"is_repeat\" : null,\n" +
                "          \"call_result\" : 2,\n" +
                "          \"call_result_desc\" : null,\n" +
                "          \"call_id\" : null,\n" +
                "          \"contact_time\" : null,\n" +
                "          \"recordname\" : null,\n" +
                "          \"call_show_flag\" : null,\n" +
                "          \"record_name\" : null\n" +
                "        }\n" +
                "      }]}";
        String K="{\"objectBean\":{},\"returnCode\":\"0000\",\"beans\":[],\"returnMessage\":\"sucess\",\"bean\":{\"totalCalledCount\":\"0\",\"totalCallTimeCount\":\"0\",\"totalCallSuccessCount\":\"0\",\"batchId\":\"c536bd676a594446b73afd92c0f895a8\",\"totalCallMissCount\":\"0\"},\"object\":{}}";
        JSONObject jsonObject = JSONObject.parseObject(j);
        JSONObject jsonObject1 = JSONObject.parseObject(K);
        System.out.println(jsonObject);
        System.out.println(jsonObject1);
        JSONArray hits = jsonObject.getJSONArray("hits");
        JSONArray data = jsonObject1.getJSONArray("beans");
        System.out.println(hits);
        System.out.println(data);
        HashMap<String,Object> yhkhmap = new HashMap<>();
        HashMap<String,Object> jtkhmap = new HashMap<>();
        HashMap<String,Object> ljwhmap = new HashMap<>();
        HashMap<String,Object> ljjtmap = new HashMap<>();
        HashMap<String,Object> ljwjtmap = new HashMap<>();
        Integer ljwhcs = 0;
        Integer ljjtcs = 0;
        Integer zbs = 0;
        Integer hfs = 0;
        String totalCallSuccessCount ="累计接通数" ;
        String totalCallErrorCount ="累计未接通数" ;
        String totalCalledCount="累计外呼次数";
        String totalCallTimeCount="累计通话时长";
        for (int i = 0; i < hits.size(); i++) {
            JSONObject source = hits.getJSONObject(i).getJSONObject("_source");
//            //已拨客户数
            if (!yhkhmap.containsKey(source.getString("bill_id"))){
                yhkhmap.put(source.getString("bill_id"),source.getString("call_result"));
            }
//            //接听客户数
            if (!jtkhmap.containsKey(source.getString("bill_id"))&& StringUtils.equals(source.getString("call_result"),"1")){
                jtkhmap.put(source.getString("bill_id"),source.getString("call_result"));
            }
//            //累计外呼次数
            if (source.getString("call_result")!=null){
                ljwhcs++;
            }
            //累计接通次数
            if (source.getString("call_result")!=null&& StringUtils.equals(source.getString("call_result"),"1")){
                ljjtcs++;
            }
        }
        System.out.println("已拨客户数:"+yhkhmap.size()+"已接通客户数:"+jtkhmap.size()+"累计外呼次数:"+ljwhcs+"累计接通次数:"+ljjtcs);

        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonObject2 = data.getJSONObject(i);
            if (!yhkhmap.containsKey(jsonObject2.getString("bill_id"))){
                yhkhmap.put(jsonObject2.getString("bill_id"),jsonObject2.getString("call_result"));
            }
//            //接听客户数
            if (!jtkhmap.containsKey(jsonObject2.getString("bill_id"))&& StringUtils.equals(jsonObject2.getString("call_result"),"1")){
                jtkhmap.put(jsonObject2.getString("bill_id"),jsonObject2.getString("call_result"));
            }

        }
        JSONObject bean = jsonObject1.getJSONObject("bean");
        totalCallSuccessCount = bean.getString("totalCallSuccessCount");
        totalCalledCount = bean.getString("totalCalledCount");
        totalCallTimeCount = bean.getString("totalCallTimeCount");//累计呼叫时长
        totalCallErrorCount = bean.getString("totalCallMissCount");
        ljwhcs=ljwhcs+Integer.valueOf(totalCalledCount);//累计外呼次数
        ljjtcs=ljjtcs+Integer.valueOf(totalCallSuccessCount);//累计接通次数
        Integer i = Integer.valueOf(totalCallErrorCount);
        i=ljwhcs-ljjtcs+i;//累计未接通次数
        String time = convertSecondsToTimeFormat(Integer.valueOf(totalCallTimeCount));
        System.out.println("已拨客户数:"+yhkhmap.size()+"已接通客户数:"+jtkhmap.size()+"累计外呼次数:"+ljwhcs+"累计接通次数:"+ljjtcs
        +"累计未接通次数:"+i+"累计呼叫时长:"+time);
        String s = calculatePercentage(2, 23);
        System.out.println(s);
        String s1 = convertSecondsToTimeFormat(100, 20);
        System.out.println(s1);
//        Date date = new Date("2024-02-03 12:15:55");
//        String s1 = date.toString().split("-")[0];
//        System.out.println(s1);
    }


    public static String convertSecondsToTimeFormat(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        return time;
    }
    public static String convertSecondsToTimeFormat(int seconds,int total) {
        if (total == 0||seconds==0) {
            return "00:00:00";
        }
        seconds=seconds/total;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        return time;
    }

    public static String calculatePercentage(int a, int b) {
        if (b == 0||a==0) {
            return "0.00%";
        }
        // 使用BigDecimal来确保精确计算
        BigDecimal percentage = new BigDecimal(a).divide(new BigDecimal(b), 2, RoundingMode.HALF_UP);

        // 格式化输出为百分比形式，保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("0.00%");

        // 返回格式化后的字符串
        return decimalFormat.format(percentage);
    }
}
class Main1 {
    public static void main(String[] args) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 计算前30天的日期
        LocalDate thirtyDaysAgo = currentDate.minus(30, ChronoUnit.DAYS);

        // 创建零时（00:00）
        LocalTime midnight = LocalTime.MIDNIGHT;

        // 结合日期和零时来创建LocalDateTime对象
        LocalDateTime dateTimeThirtyDaysAgoMidnight = LocalDateTime.of(thirtyDaysAgo, midnight);

        // 创建一个自定义的DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 使用formatter格式化LocalDateTime对象
        String formattedDateTime = dateTimeThirtyDaysAgoMidnight.format(formatter);

        // 打印结果
        System.out.println("前30天的零时（非标准格式）：" + formattedDateTime);

        List<Map<String,Object>>  list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        map.put("name","张三");
        map.put("age",18);
        map.put("result","1");
        map1.put("name","张三1");
        map1.put("age",181);
        map1.put("result",null);
        list.add(map);
        list.add(map1);
        for (Map<String, Object> stringObjectMap : list) {
            if (stringObjectMap.get("result") !=""&&stringObjectMap.get("result") !=null){
                System.out.println(stringObjectMap.get("age"));
                System.out.println("kong");
            }
            if (stringObjectMap.get("result") !=""&&stringObjectMap.get("result") !=null&&StringUtils.equals(stringObjectMap.get("result").toString(),"1")){
                System.out.println(stringObjectMap.get("age"));
            }
        }
    }
}


class Main2 {
    public static void main(String[] args) {
        Path directoryPath = Paths.get("C:\\Users\\tzx\\Desktop\\中移\\123");
        List<Path> tempFiles = findTempFiles(directoryPath);

        // 打印所有找到的文件
        tempFiles.forEach(System.out::println);
    }

    private static List<Path> findTempFiles(Path directoryPath) {
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            // 使用filter方法保留以"temp"开头的文件，并收集到列表中
            return paths
                    .filter(Files::isRegularFile) // 过滤出文件
                    .filter(path -> path.getFileName().toString().startsWith("temp")) // 过滤出以"temp"开头的文件
                    .collect(Collectors.toList()); // 收集到列表中
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // 如果发生错误，返回空列表
        }
    }
}
class PercentageCalculator {

    /**
     * 计算整数a相对于整数b的百分比，并返回保留两位小数的百分比值。
     *
     * @param a 分子
     * @param b 分母（不能为0）
     * @return a相对于b的百分比，保留两位小数
     * @throws IllegalArgumentException 如果b为0
     */
    public static String calculatePercentage(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        double percentage = ((double) a / b) * 100;
        // 使用BigDecimal进行精确的舍入到两位小数
        BigDecimal bd = new BigDecimal(percentage);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd+"%";
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 19;
        String percentage = calculatePercentage(a, b);
        String s = String.valueOf(percentage);
        System.out.println(percentage);
    }
}

class FileDownloadExample {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://example.com/api/recordings/123"; // 替换为实际的URL
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<String> requestEntity = new HttpEntity<>("request body if needed", headers);

        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST, // 或GET，根据你的API要求选择
                requestEntity,
                byte[].class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            byte[] fileContent = responseEntity.getBody();
            try (FileOutputStream outputStream = new FileOutputStream("downloaded_recording.wav")) { // 指定保存的文件名和格式
                outputStream.write(fileContent);
                System.out.println("File downloaded successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to download file: " + responseEntity.getStatusCode());
        }
    }
}