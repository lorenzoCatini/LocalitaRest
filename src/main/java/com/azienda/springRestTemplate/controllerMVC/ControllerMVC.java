package com.azienda.springRestTemplate.controllerMVC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.azienda.springRestTemplate.clientRest.ClientRest;
import com.azienda.springRestTemplate.exception.ComunicationException;
import com.azienda.springRestTemplate.exception.DatiNonValidiException;
import com.azienda.springRestTemplate.model.LocalitaDto;

@Controller
public class ControllerMVC {

	@Autowired
	ClientRest clientRest;
	
	@GetMapping("/elenco")
	public String elenco(Model model) {
		try {
			List<LocalitaDto> lista= clientRest.getAll();
			model.addAttribute("chiaveLista", lista);
			return "lista";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("chiaveEsito", "errore imprevisto");
			return "esito";
		}
	}
	
	@GetMapping("/vaiaCrea")
	public String vaiacrea() {
		return "crea";
	}
	@ModelAttribute("chiaveInserimento")
	public LocalitaDto initForm() {
		return new LocalitaDto();
	}
	@PostMapping("/inserisci")
	public String inserisci(@ModelAttribute("chiaveInserimento") LocalitaDto dto, Model model) {
		try {
			clientRest.inserimento(dto);
			return "redirect:/elenco";
		}catch (ComunicationException | DatiNonValidiException e) {
			e.printStackTrace();
			model.addAttribute("chiaveEsito", e.getMessage());
			return "esito";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("chiaveEsito", "errore imprevisto");
			return "esito";
		}
	}
}
