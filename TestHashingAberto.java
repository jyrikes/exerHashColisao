import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hashColisao.EstruturaHashTable;

public class TestHashingAberto {
    
    private EstruturaHashTable ha;

    @Before
    public void configurar(){
        ha = new EstruturaHashTable();
    }

    @Test
    public void testaInsertSearch(){
        assertEquals(-1 , ha.search(10));
        assertEquals(-1, ha.search(11));
        assertTrue(ha.insert(10));
        assertEquals(10, ha.search(10));
        assertEquals(-1, ha.search(11));
    }

    @Test
    public void testaDeleteSearch(){
        assertTrue(ha.insert(10));
        assertEquals(10, ha.search(10));
        assertTrue(ha.delete(10));
        assertFalse(ha.delete(11));
        assertEquals(-1, ha.search(10));
        
    }

    @Test
    public void testaDeleteColisaoSearch(){
        //Não devem existir
        assertEquals(-1, ha.search(10));
        assertEquals(-1, ha.search(1010));
        assertEquals(-1, ha.search(2010));
        
        //Inserindo
        assertTrue(ha.insert(10));
        assertTrue(ha.insert(1010));
        assertTrue(ha.insert(2010));
        
        //Verificando inserção
        assertEquals(10, ha.search(10));
        assertTrue(ha.search(1010) != -1);
        assertTrue(ha.search(2010) != -1);
        assertEquals(-1, ha.search(3010));

        //Delete
        assertTrue(ha.delete(10));
        assertTrue(ha.delete(1010)); //Colisão vai para o porão
        assertFalse(ha.delete(3010)); //Colisão vai para o porão

        //Verificando delete
        assertEquals(-1, ha.search(10));
        assertEquals(-1, ha.search(1010));
        assertTrue(ha.search(2010) != -1);
        
    }
}