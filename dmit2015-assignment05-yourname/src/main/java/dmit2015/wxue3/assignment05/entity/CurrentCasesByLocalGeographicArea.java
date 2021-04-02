package dmit2015.wxue3.assignment05.entity;
/**
 * This is current cases by local geographic area entity class, which matches the column of the data from government website
 *
 * @author  Wanlun Xue
 * @version 1.0
 * @lastModified   2021.04.01
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class CurrentCasesByLocalGeographicArea implements Serializable {

    @Id
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer totalCases;

    @Column(nullable = false)
    private Integer activeCases;

    @Column(nullable = false)
    private Integer recoveredCases;

    @Column(nullable = false)
    private Integer deaths;

    @Column(nullable = false)
    private org.geolatte.geom.Polygon<org.geolatte.geom.G2D> polygon;
}
