package ch.hevs.design.data.converter;

/**
 * Created by maxim on 10.05.2017.
 */

public class MouvementConverter {
    public static ch.hevs.design.data.Mouvement CloudToLocal(ch.hevs.design.backend.mouvementApi.model.Mouvement cep){
        ch.hevs.design.data.Mouvement res = new ch.hevs.design.data.Mouvement();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setQte(cep.getQte());
        res.setIn(cep.getIn());
        res.setVin(VinConverter.CloudToLocal(cep.getVin()));

        return res;
    }

    public static ch.hevs.design.backend.mouvementApi.model.Mouvement LocalToCloud(ch.hevs.design.data.Mouvement cep){
        ch.hevs.design.backend.mouvementApi.model.Mouvement res = new ch.hevs.design.backend.mouvementApi.model.Mouvement();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setQte(cep.getQte());
        res.setIn(cep.isIn());
        res.setVin(VinConverter.LocalToCloudMouvement(cep.getVin()));
        return res;
    }
}
