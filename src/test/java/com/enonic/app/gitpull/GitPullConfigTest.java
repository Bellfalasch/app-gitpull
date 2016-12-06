package com.enonic.app.gitpull;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

import static org.junit.Assert.*;

public class GitPullConfigTest
{
    @Test
    public void testCreate()
    {
        final Map<String, String> props = Maps.newHashMap();
        props.put( "a", "1" );

        props.put( "b.uri", "uri" );

        props.put( "c.uri", "uri" );
        props.put( "c.dir", "dir" );

        props.put( "d.uri", "uri" );
        props.put( "d.dir", "dir" );
        props.put( "d.user", "user" );
        props.put( "d.password", "password" );

        final GitPullConfig config = GitPullConfig.create( props );
        final Map<String, GitPullEntry> entries = config.toMap();

        assertEquals( 2, entries.size() );

        final GitPullEntry entry1 = entries.get( "c" );
        assertNotNull( entry1 );
        assertEquals( "c", entry1.name );
        assertEquals( "uri", entry1.uri );
        assertEquals( "dir", entry1.dir.getName() );
        assertNull( entry1.user );
        assertNull( entry1.password );

        final GitPullEntry entry2 = entries.get( "d" );
        assertNotNull( entry2 );
        assertEquals( "d", entry2.name );
        assertEquals( "uri", entry2.uri );
        assertEquals( "dir", entry2.dir.getName() );
        assertEquals( "user", entry2.user );
        assertEquals( "password", entry2.password );
    }
}
