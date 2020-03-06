package c.tgm.booksapplication;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

import c.tgm.booksapplication.main.MainActivityPresenter;
import c.tgm.booksapplication.main.MainActivityView;
import c.tgm.booksapplication.main.MainModel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MainPresenterTest extends BaseTest {
    
    private MainActivityPresenter mPresenter;
    
    @Mock
    private MainActivityView view;
    
    private MainModel mModel = new MainModel();
    
    @Mock
    private MainModel model;
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
    
        mPresenter = new MainActivityPresenter();
    }
    
    @Test
    public void PushItem_ModelUpdated(){
        String item = "some test item";
        String item_2 = "some test item 2";
        String item_3 = "some test item 3";
    
        model.pushItem(item);
    
        model.pushItem(item_2);
        
        model.pushItem(item_3);
    
        List<String> list = new ArrayList<String>();
        list.add(item);
        list.add(item_2);
        list.add(item_3);
        
        when(model.getItems()).thenReturn(list);
    }
    
    @Test
    public void PushItem_ViewListUpdateCalled(){
        String item = "some test item";
        mPresenter.setView(view);
        mPresenter.addItem(item);
    
        List<String> list = new ArrayList<String>();
        list.add(item);
        
        verify(view).updateList(list);
    }
}
