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
        name = "commandApi",
        version = "v1",
        resource = "command",
        namespace = @ApiNamespace(
                ownerDomain = "backend.design.hevs.ch",
                ownerName = "backend.design.hevs.ch",
                packagePath = ""
        )
)
public class CommandEndpoint {

    private static final Logger logger = Logger.getLogger(CommandEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Command.class);
    }

    /**
     * Returns the {@link Command} with the corresponding ID.
     *
     * @param _id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Command} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "command/{_id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Command get(@Named("_id") long _id) throws NotFoundException {
        logger.info("Getting Command with ID: " + _id);
        Command command = ofy().load().type(Command.class).id(_id).now();
        if (command == null) {
            throw new NotFoundException("Could not find Command with ID: " + _id);
        }
        return command;
    }

    /**
     * Inserts a new {@code Command}.
     */
    @ApiMethod(
            name = "insert",
            path = "command",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Command insert(Command command) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that command._id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entities(command.getVin()).now();
        ofy().save().entity(command).now();
        logger.info("Created Command with ID: " + command.get_id());

        return ofy().load().entity(command).now();
    }

    /**
     * Updates an existing {@code Command}.
     *
     * @param _id     the ID of the entity to be updated
     * @param command the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Command}
     */
    @ApiMethod(
            name = "update",
            path = "command/{_id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Command update(@Named("_id") long _id, Command command) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(_id);
        ofy().save().entity(command).now();
        logger.info("Updated Command: " + command);
        return ofy().load().entity(command).now();
    }

    /**
     * Deletes the specified {@code Command}.
     *
     * @param _id the ID of the entity to delete
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Command}
     */
    @ApiMethod(
            name = "remove",
            path = "command/{_id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("_id") long _id) throws NotFoundException {
        checkExists(_id);
        ofy().delete().type(Command.class).id(_id).now();
        logger.info("Deleted Command with ID: " + _id);
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
            path = "command",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Command> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Command> query = ofy().load().type(Command.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Command> queryIterator = query.iterator();
        List<Command> commandList = new ArrayList<Command>(limit);
        while (queryIterator.hasNext()) {
            commandList.add(queryIterator.next());
        }
        return CollectionResponse.<Command>builder().setItems(commandList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long _id) throws NotFoundException {
        try {
            ofy().load().type(Command.class).id(_id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Command with ID: " + _id);
        }
    }
}