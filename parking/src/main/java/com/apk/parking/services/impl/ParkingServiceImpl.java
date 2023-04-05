package com.apk.parking.services.impl;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.apk.parking.dao.ParkingAPIDAO;
import com.apk.parking.dao.entity.RecordsEntity;
import com.apk.parking.dao.entity.ResponseParkingAPIentity;
import com.apk.parking.models.Parking;
import com.apk.parking.services.ParkingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    public ParkingAPIDAO parkingAPIDAO;

    @Override
    public List<Parking> getListParkings() {
       ResponseParkingAPIentity reponse = parkingAPIDAO.getListParkings();
        return transformEntityToModel(reponse);
    }

    private List<Parking> transformEntityToModel(ResponseParkingAPIentity reponse) {
        List<Parking> resultat = new ArrayList<Parking>();
        for (RecordsEntity record : reponse.getRecords()) {
            Parking parking = new Parking();
            parking.setNom(record.getFields().getGrpNom());
            parking.setStatus(getLibelleStatut(record));
            parking.setNbPlacesDispo(record.getFields().getGrpDisponible());
            parking.setNbPlaceTotal(record.getFields().getGrpExploitation());
            parking.setHeureMaj(getHeureMaj(record));
            resultat.add(parking);

        }
        return resultat;
    }

    private String getHeureMaj(RecordsEntity record) {
        OffsetDateTime dateMaj = OffsetDateTime.parse(record.getFields().getGrpHorodatage());
        OffsetDateTime dateMajWithOffsetPlus2 = dateMaj.withOffsetSameInstant(ZoneOffset.of("+02:00"));
        return dateMajWithOffsetPlus2.getHour() + "h" + dateMajWithOffsetPlus2.getMinute();
    }

    private String getLibelleStatut(RecordsEntity record) {
        switch(record.getFields().getGrpStatut()) {
            case "1": {
                return "FERME";
            }
            case "2": {
                return "ABONNES";
            }
            case "5": {
                return "OUVERT";
            }

        }
        return "Données non disponibles";
    }
    
}
