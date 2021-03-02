package luorijun.learn.springdataredis.entities;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private GoodsInfo goods;

    private int count;
    private float total;

    private LocalDateTime created;

    @PrePersist
    private void onInsert() {
        created = LocalDateTime.now();
    }
}
