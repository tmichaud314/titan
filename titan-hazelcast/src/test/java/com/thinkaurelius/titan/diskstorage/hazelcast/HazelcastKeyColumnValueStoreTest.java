package com.thinkaurelius.titan.diskstorage.hazelcast;

import com.thinkaurelius.titan.HazelcastStorageSetup;
import com.thinkaurelius.titan.diskstorage.KeyColumnValueStoreTest;
import com.thinkaurelius.titan.diskstorage.StorageException;
import com.thinkaurelius.titan.diskstorage.keycolumnvalue.ConsistencyLevel;
import com.thinkaurelius.titan.diskstorage.keycolumnvalue.KeyColumnValueStoreManager;

import static com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration.STORAGE_NAMESPACE;

public class HazelcastKeyColumnValueStoreTest extends KeyColumnValueStoreTest {

    public HazelcastKeyColumnValueStoreTest() throws StorageException {
        manager = openStorageManager();
        store = manager.openDatabase(STORE_NAME);
    }

    @Override
    public KeyColumnValueStoreManager openStorageManager() throws StorageException {
        return new HazelcastKeyColumnValueStoreManager(HazelcastStorageSetup.getHazelcastGraphConfig().subset(STORAGE_NAMESPACE));
    }

    @Override
    public void setUp() throws StorageException {
        tx = manager.beginTransaction(ConsistencyLevel.DEFAULT);
    }

    @Override
    public void tearDown() throws Exception {
        if (tx != null)
            tx.commit();
        manager.clearStorage();
    }

    @Override
    public void clopen() {
    }

    @Override
    public void close() {
    }
}