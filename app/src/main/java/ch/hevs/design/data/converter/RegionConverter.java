package ch.hevs.design.data.converter;

/**
 * Created by maxim on 10.05.2017.
 */

public class RegionConverter {
    public static ch.hevs.design.data.Region CloudToLocal(ch.hevs.design.backend.regionApi.model.Region cep){
        ch.hevs.design.data.Region res = new ch.hevs.design.data.Region();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.CloudToLocal(cep.getPays()));
        return res;
    }
    public static ch.hevs.design.data.Region CloudToLocal(ch.hevs.design.backend.vinApi.model.Region cep){
        ch.hevs.design.data.Region res = new ch.hevs.design.data.Region();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.CloudToLocal(cep.getPays()));
        return res;
    }
    public static ch.hevs.design.data.Region CloudToLocal(ch.hevs.design.backend.commandApi.model.Region cep){
        ch.hevs.design.data.Region res = new ch.hevs.design.data.Region();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.CloudToLocal(cep.getPays()));
        return res;
    }
    public static ch.hevs.design.data.Region CloudToLocal(ch.hevs.design.backend.mouvementApi.model.Region cep){
        ch.hevs.design.data.Region res = new ch.hevs.design.data.Region();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.CloudToLocal(cep.getPays()));
        return res;
    }


    public static ch.hevs.design.backend.regionApi.model.Region LocalToCloud(ch.hevs.design.data.Region cep){
        ch.hevs.design.backend.regionApi.model.Region res = new ch.hevs.design.backend.regionApi.model.Region();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.LocalToCloudRegion(cep.getPays()));
        return res;
    }
    public static ch.hevs.design.backend.vinApi.model.Region LocalToCloudVin(ch.hevs.design.data.Region cep){
        ch.hevs.design.backend.vinApi.model.Region res = new ch.hevs.design.backend.vinApi.model.Region();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.LocalToCloudVin(cep.getPays()));
        return res;
    }
    public static ch.hevs.design.backend.commandApi.model.Region LocalToCloudCommand(ch.hevs.design.data.Region cep){
        ch.hevs.design.backend.commandApi.model.Region res = new ch.hevs.design.backend.commandApi.model.Region();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.LocalToCloudCommand(cep.getPays()));
        return res;
    }
    public static ch.hevs.design.backend.mouvementApi.model.Region LocalToCloudMouvement(ch.hevs.design.data.Region cep){
        ch.hevs.design.backend.mouvementApi.model.Region res = new ch.hevs.design.backend.mouvementApi.model.Region();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setNom(cep.getNom());
        res.setPays(PaysConverter.LocalToCloudMouvement(cep.getPays()));
        return res;
    }
}
