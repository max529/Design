package ch.hevs.design.data.converter;

/**
 * Created by maxim on 10.05.2017.
 */

public class ProviderConverter {
    public static ch.hevs.design.data.Provider CloudToLocal(ch.hevs.design.backend.providerApi.model.Provider cep){
        ch.hevs.design.data.Provider res = new ch.hevs.design.data.Provider();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }
    public static ch.hevs.design.data.Provider CloudToLocal(ch.hevs.design.backend.vinApi.model.Provider cep){
        ch.hevs.design.data.Provider res = new ch.hevs.design.data.Provider();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }
    public static ch.hevs.design.data.Provider CloudToLocal(ch.hevs.design.backend.commandApi.model.Provider cep){
        ch.hevs.design.data.Provider res = new ch.hevs.design.data.Provider();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }
    public static ch.hevs.design.data.Provider CloudToLocal(ch.hevs.design.backend.mouvementApi.model.Provider cep){
        ch.hevs.design.data.Provider res = new ch.hevs.design.data.Provider();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }



    public static ch.hevs.design.backend.providerApi.model.Provider LocalToCloud(ch.hevs.design.data.Provider cep){
        ch.hevs.design.backend.providerApi.model.Provider res = new ch.hevs.design.backend.providerApi.model.Provider();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }
    public static ch.hevs.design.backend.vinApi.model.Provider LocalToCloudVin(ch.hevs.design.data.Provider cep){
        ch.hevs.design.backend.vinApi.model.Provider res = new ch.hevs.design.backend.vinApi.model.Provider();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }
    public static ch.hevs.design.backend.commandApi.model.Provider LocalToCloudCommand(ch.hevs.design.data.Provider cep){
        ch.hevs.design.backend.commandApi.model.Provider res = new ch.hevs.design.backend.commandApi.model.Provider();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }
    public static ch.hevs.design.backend.mouvementApi.model.Provider LocalToCloudMouvement(ch.hevs.design.data.Provider cep){
        ch.hevs.design.backend.mouvementApi.model.Provider res = new ch.hevs.design.backend.mouvementApi.model.Provider();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setAdress(cep.getAdress());
        res.setEmail(cep.getEmail());
        res.setName(cep.getName());
        res.setSurname(cep.getSurname());
        return res;
    }
}
