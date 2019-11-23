package cn.tedu.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class WqBlogUIResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    //查询数据总数量
    private Integer total;
    //分页查询的list结果
    private List<?> rows;

    public WqBlogUIResult() {
    }

    public WqBlogUIResult(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public WqBlogUIResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static WqBlogUIResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("rows");
            List<?> list = null;
            if (data.isArray() && data.size() > 0) {
                list = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return new WqBlogUIResult(jsonNode.get("total").intValue(), list);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
