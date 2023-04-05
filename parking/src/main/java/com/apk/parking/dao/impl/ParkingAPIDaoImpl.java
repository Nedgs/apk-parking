package com.apk.parking.dao.impl;

import com.apk.parking.dao.ParkingAPIDAO;
import com.apk.parking.dao.entity.ResponseParkingAPIentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ParkingAPIDaoImpl implements ParkingAPIDAO {

	private static final String URL_API_OPEN_DATA = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&q=&rows=-1&facet=grp_nom&facet=grp_statut";
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseParkingAPIentity getListParkings() {
		return restTemplate.getForEntity(URL_API_OPEN_DATA, ResponseParkingAPIentity.class).getBody();
	}

}
