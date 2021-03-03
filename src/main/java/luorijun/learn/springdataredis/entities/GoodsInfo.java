package luorijun.learn.springdataredis.entities;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class GoodsInfo {
    private int id;
    private String name;
    private float price;
}
