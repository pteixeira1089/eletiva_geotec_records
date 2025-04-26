package com.eemarisademello.eletiva_geotec_records.model;

import com.eemarisademello.eletiva_geotec_client.dto.FeelingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feeling", schema = "records")
public class Feeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeling_id")
    private Long feelingId;

    @Column(name="feeling")
    private String feeling;

    public static Feeling convert(FeelingDTO feelingDTO) {
        Feeling feeling = new Feeling();
        feeling.setFeelingId(feelingDTO.getFeelingId());
        feeling.setFeeling(feelingDTO.getFeeling());
        return feeling;
    }
}
