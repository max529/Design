package ch.hevs.design.data.converter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 10.05.2017.
 */

public class VinConverter {
    public static ch.hevs.design.data.Vin CloudToLocal(ch.hevs.design.backend.vinApi.model.Vin cep){
        ch.hevs.design.data.Vin res = new ch.hevs.design.data.Vin();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setAnnee(cep.getAnnee());

        List<ch.hevs.design.data.Cepage> cs = new ArrayList<>();
        Log.e("de",cep.getCepage().toString());
        for (ch.hevs.design.backend.vinApi.model.Cepage c : cep.getCepage()) {
            cs.add(CepageConverter.CloudToLocal(c));
        }
        Log.e("de",cs.toString());
        res.setCepage(cs);

        res.setName(cep.getName());
        res.setImg(cep.getImg());
        res.setProvider(ProviderConverter.CloudToLocal(cep.getProvider()));
        res.setQte(cep.getQte());
        res.setCouleur(CouleurConverter.CloudToLocal(cep.getCouleur()));
        res.setDescription(cep.getDescription());
        res.setPrix(cep.getPrix());
        res.setRegion(RegionConverter.CloudToLocal(cep.getRegion()));

        Log.e("de",res.toStringInfo());

        return res;
    }
    public static ch.hevs.design.data.Vin CloudToLocal(ch.hevs.design.backend.commandApi.model.Vin cep){
        ch.hevs.design.data.Vin res = new ch.hevs.design.data.Vin();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setAnnee(cep.getAnnee());

        List<ch.hevs.design.data.Cepage> cs = new ArrayList<>();
        for (ch.hevs.design.backend.commandApi.model.Cepage c : cep.getCepage()) {
            cs.add(CepageConverter.CloudToLocal(c));
        }
        res.setCepage(cs);

        res.setName(cep.getName());
        res.setImg(cep.getImg());
        res.setProvider(ProviderConverter.CloudToLocal(cep.getProvider()));
        res.setQte(cep.getQte());
        res.setCouleur(CouleurConverter.CloudToLocal(cep.getCouleur()));
        res.setDescription(cep.getDescription());
        res.setPrix(cep.getPrix());
        res.setRegion(RegionConverter.CloudToLocal(cep.getRegion()));

        return res;
    }
    public static ch.hevs.design.data.Vin CloudToLocal(ch.hevs.design.backend.mouvementApi.model.Vin cep){
        ch.hevs.design.data.Vin res = new ch.hevs.design.data.Vin();
        res.set_id(Integer.parseInt(cep.getId()+""));
        res.setAnnee(cep.getAnnee());

        List<ch.hevs.design.data.Cepage> cs = new ArrayList<>();
        for (ch.hevs.design.backend.mouvementApi.model.Cepage c : cep.getCepage()) {
            cs.add(CepageConverter.CloudToLocal(c));
        }
        res.setCepage(cs);

        res.setName(cep.getName());
        res.setImg(cep.getImg());
        res.setProvider(ProviderConverter.CloudToLocal(cep.getProvider()));
        res.setQte(cep.getQte());
        res.setCouleur(CouleurConverter.CloudToLocal(cep.getCouleur()));
        res.setDescription(cep.getDescription());
        res.setPrix(cep.getPrix());
        res.setRegion(RegionConverter.CloudToLocal(cep.getRegion()));

        return res;
    }




    public static ch.hevs.design.backend.vinApi.model.Vin LocalToCloud(ch.hevs.design.data.Vin cep){
        ch.hevs.design.backend.vinApi.model.Vin res = new ch.hevs.design.backend.vinApi.model.Vin();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setAnnee(cep.getAnnee());

        List<ch.hevs.design.backend.vinApi.model.Cepage> cs = new ArrayList<>();
        for (ch.hevs.design.data.Cepage c : cep.getCepage()) {
            cs.add(CepageConverter.LocalToCloudVin(c));
        }
        res.setCepage(cs);

        res.setName(cep.getName());
        res.setImg(cep.getImg());
        res.setProvider(ProviderConverter.LocalToCloudVin(cep.getProvider()));
        res.setQte(cep.getQte());
        res.setCouleur(CouleurConverter.LocalToCloudVin(cep.getCouleur()));
        res.setDescription(cep.getDescription());
        res.setPrix(cep.getPrix());
        res.setRegion(RegionConverter.LocalToCloudVin(cep.getRegion()));
        Log.e("debug vin ",cep.toStringInfo());
        return res;
    }
    public static ch.hevs.design.backend.commandApi.model.Vin LocalToCloudCommand(ch.hevs.design.data.Vin cep){
        ch.hevs.design.backend.commandApi.model.Vin res = new ch.hevs.design.backend.commandApi.model.Vin();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setAnnee(cep.getAnnee());

        List<ch.hevs.design.backend.commandApi.model.Cepage> cs = new ArrayList<>();
        for (ch.hevs.design.data.Cepage c : cep.getCepage()) {
            cs.add(CepageConverter.LocalToCloudCommand(c));
        }
        res.setCepage(cs);

        res.setName(cep.getName());
        res.setImg(cep.getImg());
        res.setProvider(ProviderConverter.LocalToCloudCommand(cep.getProvider()));
        res.setQte(cep.getQte());
        res.setCouleur(CouleurConverter.LocalToCloudCommand(cep.getCouleur()));
        res.setDescription(cep.getDescription());
        res.setPrix(cep.getPrix());
        res.setRegion(RegionConverter.LocalToCloudCommand(cep.getRegion()));
        return res;
    }
    public static ch.hevs.design.backend.mouvementApi.model.Vin LocalToCloudMouvement(ch.hevs.design.data.Vin cep){
        ch.hevs.design.backend.mouvementApi.model.Vin res = new ch.hevs.design.backend.mouvementApi.model.Vin();
        res.setId(Long.parseLong(cep.get_id()+""));
        res.setAnnee(cep.getAnnee());

        List<ch.hevs.design.backend.mouvementApi.model.Cepage> cs = new ArrayList<>();
        for (ch.hevs.design.data.Cepage c : cep.getCepage()) {
            cs.add(CepageConverter.LocalToCloudMouvement(c));
        }
        res.setCepage(cs);

        res.setName(cep.getName());
        res.setImg(cep.getImg());
        res.setProvider(ProviderConverter.LocalToCloudMouvement(cep.getProvider()));
        res.setQte(cep.getQte());
        res.setCouleur(CouleurConverter.LocalToCloudMouvement(cep.getCouleur()));
        res.setDescription(cep.getDescription());
        res.setPrix(cep.getPrix());
        res.setRegion(RegionConverter.LocalToCloudMouvement(cep.getRegion()));
        return res;
    }
}
