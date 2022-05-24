package com.azienda.springRestTemplate.clientRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.azienda.springRestTemplate.exception.ComunicationException;
import com.azienda.springRestTemplate.exception.DatiNonValidiException;
import com.azienda.springRestTemplate.model.LocalitaDto;

@Component
public class ClientRest {
	
	private final static String BASE_URL = "http://localhost:8080/rest/localita"; 
	
	@Autowired
	private RestTemplate restTemplate;
	
	private HttpHeaders getBaseHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return httpHeaders;
	}

	public List<LocalitaDto> getAll(){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<LocalitaDto[]> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/getAll",HttpMethod.GET,httpEntity,LocalitaDto[].class);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto[] localita = responseEntityLocalita.getBody();
				return Arrays.asList(localita);
			}
			return new ArrayList<LocalitaDto>();
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ArrayList<LocalitaDto>();
		}
	}
	
	public List<LocalitaDto> getAllWithGetForObject(){
		try {
			LocalitaDto[] localita = restTemplate.getForObject(BASE_URL +"/getAll",LocalitaDto[].class);
			return Arrays.asList(localita);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ArrayList<LocalitaDto>();
		}
	}
	
	public LocalitaDto getById(Integer id){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<LocalitaDto> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/getById/{id}",HttpMethod.GET,httpEntity,LocalitaDto.class,id);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto localita = responseEntityLocalita.getBody();
				return localita;
			}
			return null;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public LocalitaDto getByIdWithGetForEntity(Integer id){
		try {
			ResponseEntity<LocalitaDto> responseEntityLocalita = restTemplate.getForEntity(BASE_URL +"/getById/{id}",LocalitaDto.class,id);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto localita = responseEntityLocalita.getBody();
				return localita;
			}
			return null;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<LocalitaDto> getByNome(String nome){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<LocalitaDto[]> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/getByNome/{nome}",HttpMethod.GET,httpEntity,LocalitaDto[].class,nome);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto[] localita = responseEntityLocalita.getBody();
				return Arrays.asList(localita);
			}
			return new ArrayList<LocalitaDto>();
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ArrayList<LocalitaDto>();
		}
	}
	
	public List<LocalitaDto> getByTemperaturaInferiore(Float temperatura){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<LocalitaDto[]> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/getByTemperaturaInferiore/{temperatura}",HttpMethod.GET,httpEntity,LocalitaDto[].class,temperatura);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto[] localita = responseEntityLocalita.getBody();
				return Arrays.asList(localita);
			}
			return new ArrayList<LocalitaDto>();
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ArrayList<LocalitaDto>();
		}
	}
	
	public List<LocalitaDto>  getByTemperaturaSuperiore(Float temperatura){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<LocalitaDto[]> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/getByTemperaturaSuperiore/{temperatura}",HttpMethod.GET,httpEntity,LocalitaDto[].class,temperatura);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto[] localita = responseEntityLocalita.getBody();
				return Arrays.asList(localita);
			}
			return new ArrayList<LocalitaDto>();
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ArrayList<LocalitaDto>();
		}
	}
		
	public LocalitaDto inserimento(LocalitaDto localita) throws DatiNonValidiException, ComunicationException {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<LocalitaDto> httpEntity = new HttpEntity<LocalitaDto>(localita,httpHeaders);
			ResponseEntity<LocalitaDto> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/registraLocalita",HttpMethod.POST,httpEntity,LocalitaDto.class);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.CREATED) ) {
				LocalitaDto local = responseEntityLocalita.getBody();
				return local;
			}
			return null;
		} catch (HttpClientErrorException e) {
			if(e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				throw new DatiNonValidiException("campi incompleti",e);
			}
			throw new ComunicationException("codice http imprevisto",e);
		}
	}
	
	public LocalitaDto inserimentoConPostForObject(LocalitaDto localita) {
		try {
			LocalitaDto local = restTemplate.postForObject(BASE_URL +"/registraLocalita",localita,LocalitaDto.class);
			return local;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public LocalitaDto inserimentoConPostForEntity(LocalitaDto localita) {
		try {
			ResponseEntity<LocalitaDto> responseEntity = restTemplate.postForEntity(BASE_URL +"/registraLocalita",localita,LocalitaDto.class);
			if ( responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
				return responseEntity.getBody();
			}
			return null;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void inserimentoMassivo(List<LocalitaDto> localita) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<List<LocalitaDto>> httpEntity = new HttpEntity<List<LocalitaDto>>(localita,httpHeaders);
			ResponseEntity<Void> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/registraLocalitaMassivo",HttpMethod.POST,httpEntity,Void.class);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.CREATED) ) {
				System.out.println("Inserimento massivo effettuato");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public LocalitaDto aggiornamentoCompleto(LocalitaDto localita) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<LocalitaDto> httpEntity = new HttpEntity<LocalitaDto>(localita,httpHeaders);
			ResponseEntity<LocalitaDto> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/aggiornamentoCompleto",HttpMethod.PUT,httpEntity,LocalitaDto.class);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto local = responseEntityLocalita.getBody();
				return local;
			}
			return null;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void aggiornamentoCompletoConPut(LocalitaDto localita) {
		try {
			restTemplate.put(BASE_URL +"/aggiornamentoCompleto",localita,LocalitaDto.class);
			System.out.println("Località aggiornata (completamente)");
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public LocalitaDto aggiornamentoParziale(LocalitaDto localita) {
		try {
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(requestFactory);
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<LocalitaDto> httpEntity = new HttpEntity<LocalitaDto>(localita,httpHeaders);
			ResponseEntity<LocalitaDto> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/aggiornamentoParziale",HttpMethod.PATCH,httpEntity,LocalitaDto.class);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.OK) ) {
				LocalitaDto local = responseEntityLocalita.getBody();
				return local;
			}
			return null;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public LocalitaDto aggiornamentoParzialeConPatchForObject(LocalitaDto localita) {
		try {
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(requestFactory);
			LocalitaDto local = restTemplate.patchForObject(BASE_URL +"/aggiornamentoParziale",localita,LocalitaDto.class);
			return local;
		} catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void cancellazione(Integer id) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Void> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/cancellazione/{id}",HttpMethod.DELETE,httpEntity,Void.class,id);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.NO_CONTENT) ) {
				System.out.println("Cancellazione effettuata");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void cancellazioneConDelete(Integer id) {
		try {
			restTemplate.delete(BASE_URL +"/cancellazione/{id}",id);
			System.out.println("Cancellazione effettuata");
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void cancellazionePerTemperaturaInferiore(Float temperatura) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Void> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/cancellazionePerTemperaturaInferiore/{temperatura}",HttpMethod.DELETE,httpEntity,Void.class,temperatura);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.NO_CONTENT) ) {
				System.out.println("Cancellazione effettuata");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void cancellazionePerTemperaturaMaggiore(Float temperatura) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Void> responseEntityLocalita = restTemplate.exchange(BASE_URL +"/cancellazionePerTemperaturaMaggiore/{temperatura}",HttpMethod.DELETE,httpEntity,Void.class,temperatura);
			if ( responseEntityLocalita.getStatusCode().equals(HttpStatus.NO_CONTENT) ) {
				System.out.println("Cancellazione effettuata");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
}