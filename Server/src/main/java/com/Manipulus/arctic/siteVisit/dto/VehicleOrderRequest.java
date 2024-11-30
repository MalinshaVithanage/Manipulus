package com.Manipulus.arctic.siteVisit.dto;

import com.Manipulus.arctic.siteVisit.siteVisit.SiteVisit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class VehicleOrderRequest {
    private SiteVisit siteVisit;
}
