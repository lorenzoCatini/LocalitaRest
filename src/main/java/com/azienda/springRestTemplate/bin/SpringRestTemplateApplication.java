package com.azienda.springRestTemplate.bin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.azienda.springRestTemplate.clientRest.ClientRest;
import com.azienda.springRestTemplate.model.LocalitaDto;

@SpringBootApplication(scanBasePackages = {"com.azienda.springRestTemplate.clientRest", "com.azienda.springRestTemplate.controllerMVC"})
public class SpringRestTemplateApplication {

	public static void main(String[] args) {
		try{
			ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringRestTemplateApplication.class, args);
			/*ClientRest clientRest = applicationContext.getBean("clientRest",ClientRest.class);
			
			// pulizia iniziale e test cancellazione
			clientRest.cancellazionePerTemperaturaInferiore(100F);
			
			// inizio inserimenti
			LocalitaDto localitaInserita = clientRest.inserimento(new LocalitaDto(null,"Roma",20.5F));
			System.out.println("Inserita la località: " + localitaInserita);
			clientRest.inserimentoMassivo(Arrays.asList(new LocalitaDto(null,"Milano",18.5F),new LocalitaDto(null,"Napoli",22.5F),new LocalitaDto(null,"Torino",16.5F),
					new LocalitaDto(null,"Palermo",24.5F),new LocalitaDto(null,"Firenze",19.5F),new LocalitaDto(null,"Venezia",15.5F),new LocalitaDto(null,"Bari",21.5F)));
			localitaInserita = clientRest.inserimentoConPostForEntity(new LocalitaDto(null,"Cagliari",20F));
			System.out.println("Inserita la località: " + localitaInserita);
			localitaInserita = clientRest.inserimentoConPostForObject(new LocalitaDto(null,"Genova",19F));
			System.out.println("Inserita la località: " + localitaInserita);
			
			System.out.println("Visualizzazione di tutte le località");
			clientRest.getAll().forEach(System.out::println);
			
			System.out.println("Visualizzazione ordinata per temperatura decrescente");
			List<LocalitaDto> listaLocalita = clientRest.getAll();
			Collections.sort(listaLocalita, (loc1,loc2) -> -1*(loc1.getTemperatura().compareTo(loc2.getTemperatura())));
			listaLocalita.forEach(System.out::println);
			
			List<LocalitaDto> localitaConLaN = clientRest.getByNome("n");
			localitaConLaN.forEach(System.out::println);
			if( localitaConLaN.size()>0 ) {
				System.out.println(clientRest.getById(localitaConLaN.get(0).getId()));
			}
			if( localitaConLaN.size()>0 ) {
				System.out.println(clientRest.getByIdWithGetForEntity(localitaConLaN.get(0).getId()));
			}
			
			List<LocalitaDto> localitaList1 = clientRest.getByTemperaturaInferiore(20F);
			localitaList1.forEach(System.out::println);
			localitaList1.forEach(loc-> clientRest.aggiornamentoCompleto(new LocalitaDto(loc.getId(),null,null)));
			clientRest.getAll().forEach(System.out::println);
			localitaList1.forEach(loc-> clientRest.aggiornamentoParziale(new LocalitaDto(loc.getId(),"Località_" + new Random().nextInt(10),new Random().nextFloat()*30)));
			clientRest.getAll().forEach(System.out::println);
			
			List<LocalitaDto> localitaList2 =clientRest.getByTemperaturaSuperiore(20F);
			localitaList2.forEach(System.out::println);
			localitaList2.forEach(loc-> clientRest.aggiornamentoCompletoConPut(new LocalitaDto(loc.getId(),null,null)));
			clientRest.getAll().forEach(System.out::println);
			localitaList2.forEach(loc-> clientRest.aggiornamentoParzialeConPatchForObject(new LocalitaDto(loc.getId(),"Località_" + new Random().nextInt(10),new Random().nextFloat()*30)));
			clientRest.getAll().forEach(System.out::println);
			
			if ( localitaList1.size()>0) {
				clientRest.cancellazione(localitaList1.get(0).getId());
			}
			clientRest.getAll().forEach(System.out::println);
			if ( localitaList2.size()>0) {
				clientRest.cancellazioneConDelete(localitaList2.get(0).getId());
			}
			clientRest.getAll().forEach(System.out::println);
			clientRest.cancellazionePerTemperaturaInferiore(20F);
			clientRest.getAll().forEach(System.out::println);
			clientRest.cancellazionePerTemperaturaMaggiore(20F);
			clientRest.getAll().forEach(System.out::println);
			*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}