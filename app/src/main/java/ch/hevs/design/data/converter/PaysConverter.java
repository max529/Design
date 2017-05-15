package ch.hevs.design.data.converter;

/**
 * Created by maxim on 10.05.2017.
 */

public class PaysConverter {
    public static ch.hevs.design.data.Pays CloudToLocal(ch.hevs.design.backend.paysApi.model.Pays cep){
        ch.hevs.design.data.Pays res = new ch.hevs.design.data.Pays();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.data.Pays CloudToLocal(ch.hevs.design.backend.regionApi.model.Pays cep){
        ch.hevs.design.data.Pays res = new ch.hevs.design.data.Pays();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.data.Pays CloudToLocal(ch.hevs.design.backend.vinApi.model.Pays cep){
        ch.hevs.design.data.Pays res = new ch.hevs.design.data.Pays();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.data.Pays CloudToLocal(ch.hevs.design.backend.commandApi.model.Pays cep){
        ch.hevs.design.data.Pays res = new ch.hevs.design.data.Pays();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.data.Pays CloudToLocal(ch.hevs.design.backend.mouvementApi.model.Pays cep){
        ch.hevs.design.data.Pays res = new ch.hevs.design.data.Pays();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }


    public static ch.hevs.design.backend.paysApi.model.Pays LocalToCloud(ch.hevs.design.data.Pays cep){
        ch.hevs.design.backend.paysApi.model.Pays res = new ch.hevs.design.backend.paysApi.model.Pays();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.backend.regionApi.model.Pays LocalToCloudRegion(ch.hevs.design.data.Pays cep){
        ch.hevs.design.backend.regionApi.model.Pays res = new ch.hevs.design.backend.regionApi.model.Pays();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.backend.vinApi.model.Pays LocalToCloudVin(ch.hevs.design.data.Pays cep){
        ch.hevs.design.backend.vinApi.model.Pays res = new ch.hevs.design.backend.vinApi.model.Pays();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.backend.commandApi.model.Pays LocalToCloudCommand(ch.hevs.design.data.Pays cep){
        ch.hevs.design.backend.commandApi.model.Pays res = new ch.hevs.design.backend.commandApi.model.Pays();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
    public static ch.hevs.design.backend.mouvementApi.model.Pays LocalToCloudMouvement(ch.hevs.design.data.Pays cep){
        ch.hevs.design.backend.mouvementApi.model.Pays res = new ch.hevs.design.backend.mouvementApi.model.Pays();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setInitial(cep.getInitial());
        return res;
    }
}
