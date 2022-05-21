package com.hardemic.app.utils;

import com.hardemic.app.UtilLooca;
import com.hardemic.app.services.Slack;
import com.hardemic.app.useCases.ConfigUseCase;
import com.hardemic.app.entities.Config;
import java.util.List;

public class ProcessarAlerta {

    private final Slack slack = new Slack();
    private final Logs logs = new Logs();
    private final ConfigUseCase configUseCase = new ConfigUseCase();
    private List<Config> configs;
    private final UtilLooca util = new UtilLooca();

    // Contador para alertas no slack
    private Integer fk_empresa;
    private Integer quantidadeAlertasDisco;
    private Integer quantidadeAlertasMemoria;
    private Integer quantidadeAlertasCPU;

    public ProcessarAlerta(Integer fk_empresa) {
        this.fk_empresa = fk_empresa;
        this.quantidadeAlertasDisco = 0;
        this.quantidadeAlertasMemoria = 0;
        this.quantidadeAlertasCPU = 0;
    }

    public boolean init(Double memoriaDisponivel, Double discoDisponivel, Double usoCpu) {
        try {
            configs = configUseCase.index(fk_empresa);
            boolean alerta = false;
            for (Config config : configs) {
                if (config.getNome_config().equalsIgnoreCase("alerta_ram")) {
                    if (memoriaDisponivel < config.getValor()) {
                        if (quantidadeAlertasMemoria == 2) {
                            slack.warningMessage(":computer: *" + util.getHostName() + ":* Memória em nível baixo");
                            alerta = true;
                        }
                        quantidadeAlertasMemoria++;
                    } else {
                        quantidadeAlertasMemoria = 0;
                    }
                }
                if (config.getNome_config().equalsIgnoreCase("alerta_disco")) {
                    if (discoDisponivel > config.getValor()) {
                        if (quantidadeAlertasDisco == 1) {
                            slack.warningMessage(":computer: *" + util.getHostName() + ":* pouco espaço em disco disponível");
                            alerta = true;
                        }
                        quantidadeAlertasDisco++;
                    } else {
                        quantidadeAlertasDisco = 0;
                    }
                }
                if (config.getNome_config().equalsIgnoreCase("alerta_cpu")) {
                    if (usoCpu >= config.getValor()) {
                        if (quantidadeAlertasCPU == 3) {
                            slack.warningMessage(":computer: *" + util.getHostName() + ":* Uso de cpu elevado");
                            alerta = true;
                        }
                        quantidadeAlertasCPU++;
                    } else {
                        quantidadeAlertasCPU = 0;
                    }
                }
                if (config.getNome_config().equalsIgnoreCase("alerta_gpu")) {
                    
                }
                if (config.getNome_config().equalsIgnoreCase("alerta_temp")) {

                }
                if (config.getNome_config().equalsIgnoreCase("alerta_rede")) {

                }
            }
            
            return alerta;
        } catch (Exception e) {
            System.out.println("Erro ao processar envio de alerta, erro: " + e.getMessage());
            logs.warning("Erro ao processar envio de alerta, erro: " + e.getMessage());
            return false;
        }
    }
}
