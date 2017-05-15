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
        name = "mouvementApi",
        version = "v1",
        resource = "mouvement",
        namespace = @ApiNamespace(
                ownerDomain = "backend.design.hevs.ch",
                ownerName = "backend.design.hevs.ch",
                packagePath = ""
        )
)
public class MouvementEndpoint {

    private static final Logger logger = Logger.getLogger(MouvementEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Mouvement.class);
    }

    /**
     * Returns the {@link Mouvement} with the corresponding ID.
     *
     * @param _id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Mouvement} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "mouvement/{_id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Mouvement get(@Named("_id") long _id) throws NotFoundException {
        logger.info("Getting Mouvement with ID: " + _id);
        Mouvement mouvement = ofy().load().type(Mouvement.class).id(_id).now();
        if (mouvement == null) {
            throw new NotFoundException("Could not find Mouvement with ID: " + _id);
        }
        return mouvement;
    }

    /**
     * Inserts a new {@code Mouvement}.
     */
    @ApiMethod(
            name = "insert",
            path = "mouvement",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Mouvement insert(Mouvement mouvement) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that mouvement._id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entities(mouvement.getVin()).now();
        ofy().save().entity(mouvement).now();
        logger.info("Created Mouvement with ID: " + mouvement.get_id());

        return ofy().load().entity(mouvement).now();
    }

    /**
     * Updates an existing {@code Mouvement}.
     *
     * @param _id       the ID of the entity to be updated
     * @param mouvement the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Mouvement}
     */
    @ApiMethod(
            name = "update",
            path = "mouvement/{_id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Mouvement update(@Named("_id") long _id, Mouvement mouvement) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(_id);
        ofy().save().entity(mouvement).now();
        logger.info("Updated Mouvement: " + mouvement);
        return ofy().load().entity(mouvement).now();
    }

    /**
     * Deletes the specified {@code Mouvement}.
     *
     * @param _id the ID of the entity to delete
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Mouvement}
     */
    @ApiMethod(
            name = "remove",
            path = "mouvement/{_id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("_id") long _id) throws NotFoundException {
        checkExists(_id);
        ofy().delete().type(Mouvement.class).id(_id).now();
        logger.info("Deleted Mouvement with ID: " + _id);
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
            path = "mouvement",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Mouvement> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Mouvement> query = ofy().load().type(Mouvement.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Mouvement> queryIterator = query.iterator();
        List<Mouvement> mouvementList = new ArrayList<Mouvement>(limit);
        while (queryIterator.hasNext()) {
            mouvementList.add(queryIterator.next());
        }
        return CollectionResponse.<Mouvement>builder().setItems(mouvementList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long _id) throws NotFoundException {
        try {
            ofy().load().type(Mouvement.class).id(_id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Mouvement with ID: " + _id);
        }
    }
}