package ch.hevs.design.data.converter;

/**
 * Created by maxim on 10.05.2017.
 */

public class CouleurConverter {
    public static ch.hevs.design.data.Couleur CloudToLocal(ch.hevs.design.backend.couleurApi.model.Couleur cep){
        ch.hevs.design.data.Couleur res = new ch.hevs.design.data.Couleur();
        res.set_id(Integer.parseInt(cep.getId()-1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }
    public static ch.hevs.design.data.Couleur CloudToLocal(ch.hevs.design.backend.vinApi.model.Couleur cep){
        ch.hevs.design.data.Couleur res = new ch.hevs.design.data.Couleur();
        res.set_id(Integer.parseInt(cep.getId()-1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }
    public static ch.hevs.design.data.Couleur CloudToLocal(ch.hevs.design.backend.commandApi.model.Couleur cep){
        ch.hevs.design.data.Couleur res = new ch.hevs.design.data.Couleur();
        res.set_id(Integer.parseInt(cep.getId()-1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }
    public static ch.hevs.design.data.Couleur CloudToLocal(ch.hevs.design.backend.mouvementApi.model.Couleur cep){
        ch.hevs.design.data.Couleur res = new ch.hevs.design.data.Couleur();
        res.set_id(Integer.parseInt(cep.getId()-1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }


    public static ch.hevs.design.backend.couleurApi.model.Couleur LocalToCloud(ch.hevs.design.data.Couleur cep){
        ch.hevs.design.backend.couleurApi.model.Couleur res = new ch.hevs.design.backend.couleurApi.model.Couleur();
        res.setId(Long.parseLong(cep.get_id()+1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }
    public static ch.hevs.design.backend.vinApi.model.Couleur LocalToCloudVin(ch.hevs.design.data.Couleur cep){
        ch.hevs.design.backend.vinApi.model.Couleur res = new ch.hevs.design.backend.vinApi.model.Couleur();
        res.setId(Long.parseLong(cep.get_id()+1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }
    public static ch.hevs.design.backend.commandApi.model.Couleur LocalToCloudCommand(ch.hevs.design.data.Couleur cep){
        ch.hevs.design.backend.commandApi.model.Couleur res = new ch.hevs.design.backend.commandApi.model.Couleur();
        res.setId(Long.parseLong(cep.get_id()+1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }
    public static ch.hevs.design.backend.mouvementApi.model.Couleur LocalToCloudMouvement(ch.hevs.design.data.Couleur cep){
        ch.hevs.design.backend.mouvementApi.model.Couleur res = new ch.hevs.design.backend.mouvementApi.model.Couleur();
        res.setId(Long.parseLong(cep.get_id()+1+""));
        res.setCouleur(cep.getCouleur());
        return res;
    }
}
