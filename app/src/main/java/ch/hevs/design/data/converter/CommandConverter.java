package ch.hevs.design.data.converter;

/**
 * Created by maxim on 10.05.2017.
 */

public class CommandConverter {
    public static ch.hevs.design.data.Command CloudToLocal(ch.hevs.design.backend.commandApi.model.Command cep){
        ch.hevs.design.data.Command res = new ch.hevs.design.data.Command();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setQte(cep.getQte());
        res.setState(cep.getState());
        res.setVin(VinConverter.CloudToLocal(cep.getVin()));
        return res;
    }

    public static ch.hevs.design.backend.commandApi.model.Command LocalToCloud(ch.hevs.design.data.Command cep){
        ch.hevs.design.backend.commandApi.model.Command res = new ch.hevs.design.backend.commandApi.model.Command();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setQte(cep.getQte());
        res.setState(cep.getState());
        res.setVin(VinConverter.LocalToCloudCommand(cep.getVin()));
        return res;
    }
}
