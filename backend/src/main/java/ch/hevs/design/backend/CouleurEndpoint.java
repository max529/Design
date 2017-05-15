package ch.hevs.design.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "couleurApi",
        version = "v1",
        resource = "couleur",
        namespace = @ApiNamespace(
                ownerDomain = "backend.design.hevs.ch",
                ownerName = "backend.design.hevs.ch",
                packagePath = ""
        )
)
public class CouleurEndpoint {

    private static final Logger logger = Logger.getLogger(CouleurEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Couleur.class);
    }

    /**
     * Returns the {@link Couleur} with the corresponding ID.
     *
     * @param _id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Couleur} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "couleur/{_id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Couleur get(@Named("_id") long _id) throws NotFoundException {
        logger.info("Getting Couleur with ID: " + _id);
        Couleur couleur = ofy().load().type(Couleur.class).id(_id).now();
        if (couleur == null) {
            throw new NotFoundException("Could not find Couleur with ID: " + _id);
        }
        return couleur;
    }

    /**
     * Inserts a new {@code Couleur}.
     */
    @ApiMethod(
            name = "insert",
            path = "couleur",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Couleur insert(Couleur couleur) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that couleur._id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(couleur).now();
        logger.info("Created Couleur with ID: " + couleur.get_id());

        return ofy().load().entity(couleur).now();
    }

    /**
     * Updates an existing {@code Couleur}.
     *
     * @param _id     the ID of the entity to be updated
     * @param couleur the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Couleur}
     */
    @ApiMethod(
            name = "update",
            path = "couleur/{_id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Couleur update(@Named("_id") long _id, Couleur couleur) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(_id);
        ofy().save().entity(couleur).now();
        logger.info("Updated Couleur: " + couleur);
        return ofy().load().entity(couleur).now();
    }

    /**
     * Deletes the specified {@code Couleur}.
     *
     * @param _id the ID of the entity to delete
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Couleur}
     */
    @ApiMethod(
            name = "remove",
            path = "couleur/{_id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("_id") long _id) throws NotFoundException {
        checkExists(_id);
        ofy().delete().type(Couleur.class).id(_id).now();
        logger.info("Deleted Couleur with ID: " + _id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "couleur",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Couleur> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Couleur> query = ofy().load().type(Couleur.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Couleur> queryIterator = query.iterator();
        List<Couleur> couleurList = new ArrayList<Couleur>(limit);
        while (queryIterator.hasNext()) {
            couleurList.add(queryIterator.next());
        }
        return CollectionResponse.<Couleur>builder().setItems(couleurList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long _id) throws NotFoundException {
        try {
            ofy().load().type(Couleur.class).id(_id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Couleur with ID: " + _id);
        }
    }
}