package ch.hevs.design.data.converter;

/**
 * Created by maxim on 10.05.2017.
 */

public class CepageConverter {
    public static ch.hevs.design.data.Cepage CloudToLocal(ch.hevs.design.backend.cepageApi.model.Cepage cep){
        ch.hevs.design.data.Cepage res = new ch.hevs.design.data.Cepage();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        return res;
    }
    public static ch.hevs.design.data.Cepage CloudToLocal(ch.hevs.design.backend.vinApi.model.Cepage cep){
        ch.hevs.design.data.Cepage res = new ch.hevs.design.data.Cepage();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        return res;
    }
    public static ch.hevs.design.data.Cepage CloudToLocal(ch.hevs.design.backend.commandApi.model.Cepage cep){
        ch.hevs.design.data.Cepage res = new ch.hevs.design.data.Cepage();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        return res;
    }
    public static ch.hevs.design.data.Cepage CloudToLocal(ch.hevs.design.backend.mouvementApi.model.Cepage cep){
        ch.hevs.design.data.Cepage res = new ch.hevs.design.data.Cepage();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        return res;
    }


    public static ch.hevs.design.backend.cepageApi.model.Cepage LocalToCloud(ch.hevs.design.data.Cepage cep){
        ch.hevs.design.backend.cepageApi.model.Cepage res = new ch.hevs.design.backend.cepageApi.model.Cepage();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        return res;
    }
    public static ch.hevs.design.backend.vinApi.model.Cepage LocalToCloudVin(ch.hevs.design.data.Cepage cep){
        ch.hevs.design.backend.vinApi.model.Cepage res = new ch.hevs.design.backend.vinApi.model.Cepage();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        return res;
    }
    public static ch.hevs.design.backend.commandApi.model.Cepage LocalToCloudCommand(ch.hevs.design.data.Cepage cep){
        ch.hevs.design.backend.commandApi.model.Cepage res = new ch.hevs.design.backend.commandApi.model.Cepage();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        return res;
    }
    public static ch.hevs.design.backend.mouvementApi.model.Cepage LocalToCloudMouvement(ch.hevs.design.data.Cepage cep){
        ch.hevs.design.backend.mouvementApi.model.Cepage res = new ch.hevs.design.backend.mouvementApi.model.Cepage();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        return res;
    }

}
