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
        name = "vinApi",
        version = "v1",
        resource = "vin",
        namespace = @ApiNamespace(
                ownerDomain = "backend.design.hevs.ch",
                ownerName = "backend.design.hevs.ch",
                packagePath = ""
        )
)
public class VinEndpoint {

    private static final Logger logger = Logger.getLogger(VinEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Vin.class);
    }

    /**
     * Returns the {@link Vin} with the corresponding ID.
     *
     * @param _id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Vin} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "vin/{_id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Vin get(@Named("_id") long _id) throws NotFoundException {
        logger.info("Getting Vin with ID: " + _id);
        Vin vin = ofy().load().type(Vin.class).id(_id).now();
        if (vin == null) {
            throw new NotFoundException("Could not find Vin with ID: " + _id);
        }
        return vin;
    }

    /**
     * Inserts a new {@code Vin}.
     */
    @ApiMethod(
            name = "insert",
            path = "vin",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Vin insert(Vin vin) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that vin._id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entities(vin.getCepage()).now();
        ofy().save().entities(vin.getCouleur()).now();
        ofy().save().entities(vin.getProvider()).now();
        ofy().save().entities(vin.getRegion()).now();
        ofy().save().entity(vin).now();
        logger.info("Created Vin with ID: " + vin.get_id());

        return ofy().load().entity(vin).now();
    }

    /**
     * Updates an existing {@code Vin}.
     *
     * @param _id the ID of the entity to be updated
     * @param vin the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Vin}
     */
    @ApiMethod(
            name = "update",
            path = "vin/{_id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Vin update(@Named("_id") long _id, Vin vin) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(_id);
        ofy().save().entity(vin).now();
        logger.info("Updated Vin: " + vin);
        return ofy().load().entity(vin).now();
    }

    /**
     * Deletes the specified {@code Vin}.
     *
     * @param _id the ID of the entity to delete
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Vin}
     */
    @ApiMethod(
            name = "remove",
            path = "vin/{_id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("_id") long _id) throws NotFoundException {
        checkExists(_id);
        ofy().delete().type(Vin.class).id(_id).now();
        logger.info("Deleted Vin with ID: " + _id);
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
            path = "vin",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Vin> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Vin> query = ofy().load().type(Vin.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Vin> queryIterator = query.iterator();
        List<Vin> vinList = new ArrayList<Vin>(limit);
        while (queryIterator.hasNext()) {
            vinList.add(queryIterator.next());
        }
        return CollectionResponse.<Vin>builder().setItems(vinList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long _id) throws NotFoundException {
        try {
            ofy().load().type(Vin.class).id(_id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Vin with ID: " + _id);
        }
    }
}