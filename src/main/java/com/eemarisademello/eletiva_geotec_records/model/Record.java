package com.eemarisademello.eletiva_geotec_records.model;

import com.eemarisademello.eletiva_geotec_client.dto.RecordDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "record", schema = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "feeling_id", referencedColumnName = "feeling_id")
    private Feeling feeling;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "comment")
    private String comment;

    public static Record convert(RecordDTO recordDTO) {
        Record record = new Record();
        record.setRecordId(recordDTO.getRecordId());
        record.setUserId(recordDTO.getUserId());
        record.setPictureUrl(recordDTO.getPictureUrl());
        record.setCreatedAt(recordDTO.getCreatedAt());
        record.setLatitude(recordDTO.getLatitude());
        record.setLongitude(recordDTO.getLongitude());
        record.setFeeling(Feeling.convert(recordDTO.getFeeling()));
        record.setCategory(Category.convert(recordDTO.getCategory()));
        record.setComment(recordDTO.getComment());
        return record;
    }
}