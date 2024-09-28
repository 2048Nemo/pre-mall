package top.rabbitbyte.model.vo.goods.goodsDetailVo;

import lombok.Data;

@Data
public class Reply {
    private int id;
    private UserInfo simpleUser;
    private String replyUserName;
    private String createTime;
    private String content;

    // Getters and Setters
    // ...
}