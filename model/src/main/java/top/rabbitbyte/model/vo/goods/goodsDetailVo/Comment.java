package top.rabbitbyte.model.vo.goods.goodsDetailVo;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private int id;
    private UserInfo simpleUser;
    private String createTime;
    private String content;
    private List<Reply> replyList;

    // Getters and Setters
    // ...
}

