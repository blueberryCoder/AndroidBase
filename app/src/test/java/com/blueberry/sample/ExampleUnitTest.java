package com.blueberry.sample;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Builder Variants view.
 */
public class ExampleUnitTest  {

    @Mock
    LinkedList mockedList;

   @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    public void testMockitoAnnotations() throws Exception {
        assertEquals(4, 2 + 2);
       assertNotNull(mockedList);
        when(mockedList.get(10)).thenReturn("ten");
        System.out.printf(""+mockedList.get(10));

    }

    @Test
    public void testMockito() {
        LinkedList mockedList = mock(LinkedList.class);

        // 测试桩,在调用 mocked.get(0)时，返回 "first"
        when(mockedList.get(0)).thenReturn("first");
        // 调用，get(1)时抛出异常
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //输出first
        System.out.println(mockedList.get(0));
        // 抛出异常
//        System.out.println(mockedList.get(1));

        // 因为get(999)没有打桩，所以会输出null
        System.out.println(mockedList.get(999));
    }

    @Test
    public void testMockito1() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenReturn("element");

        when(mockedList.contains(argThat(isValid()))).thenReturn(true);
        System.out.println(mockedList.get(10));
        System.out.println(mockedList.contains("test"));
        verify(mockedList).get(anyInt());
        verify(mockedList).contains(argThat(isValid()));

    }

    @Test
    public void testMockito2() {
     // 验证方法被调用次数
        LinkedList mockedList = mock(LinkedList.class);
        mockedList.add("one");

        mockedList.add("two");
        mockedList.add("two");

        mockedList.add("three");
        mockedList.add("three");
        mockedList.add("three");

        verify(mockedList,times(1)).add("one");
        verify(mockedList,times(2)).add("two");
        verify(mockedList,times(3)).add("three");

        verify(mockedList,never()).add("never");
        verify(mockedList,atLeast(1)).add("one");
        verify(mockedList,atMost(10)).add("three");

        //验证 Mock对象没有交互过
        verifyZeroInteractions(mockedList,mock(LinkedList.class));
    }

    @Test
    public void testMockito3(){
       // 连续调用
        when(mockedList.get(0)).thenReturn("test1","test2","test3")
                .thenThrow(new RuntimeException());

        System.out.println("mockedList.get(0): "+mockedList.get(0));
        System.out.println("mockedList.get(0): "+mockedList.get(0));
        System.out.println("mockedList.get(0): "+mockedList.get(0));
//        System.out.println("mockedList.get(0): "+mockedList.get(0));
    }

    @Test
    public void testMockito4(){
      when(mockedList.get(anyInt())).thenAnswer(new Answer<Object>() {
          @Override
          public Object answer(InvocationOnMock invocation) throws Throwable {
              //获取参数列表
              Object[]  args = invocation.getArguments();
              //获取 Mock对象本身
              Object mock = invocation.getMock();

              return "called with arguments: "+args[0];
          }
      });
        System.out.println("mockedList.get(0) :"+mockedList.get(0));
    }

    @Test
    public void testMockito5(){
        doThrow(new RuntimeException()).when(mockedList).clear();
        doReturn(30).when(mockedList).size();
        System.out.println("size: "+mockedList.size());
//        mockedList.clear();
    }

    @Test
    public void testMockito6(){
        //监控真实对象
        List list = new LinkedList();
        List spy =  spy(list);
        // 不可能是实现,因为当调用spy.get(0)时，会调用真实对象的get(0)函数，此时会发生
        // IndexOutOfBoundException
//        when(spy.get(0)).thenReturn("foo");

        spy.add("hello");
//        doReturn("foo").when(spy).get(0);
        System.out.println("spy.get(0):"+spy.get(0));
    }

    @Test
    public void testMockito7(){
        List mock = mock(List.class);
        List mock2 = mock(List.class);
        mock.add("John");
        mock2.add("Brian");
        mock2.add("Jim");

        ArgumentCaptor argument = ArgumentCaptor.forClass(String.class);

        verify(mock).add(argument.capture());
        assertEquals("John", argument.getValue());

        verify(mock2, times(2)).add(argument.capture());

        assertEquals("Jim", argument.getValue());
        Assert.assertArrayEquals(new Object[]{"John","Brian","Jim"},argument.getAllValues().toArray());
    }



    public <T> Matcher<T> isValid() {
        return (Matcher<T>) new BaseMatcher<String>() {
            @Override
            public boolean matches(Object item) {
                return item.toString().endsWith("test");
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    @Test
    public void testMockito8(){
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = Mockito.inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

//        // B. Multiple mocks that must be used in a particular order
//        List firstMock = mock(List.class);
//        List secondMock = mock(List.class);
//
//        //using mocks
//        firstMock.add("was called first");
//        secondMock.add("was called second");
//
//        //create inOrder object passing any mocks that need to be verified in order
//        InOrder inOrder = Mockito.inOrder(firstMock, secondMock);
//
//        //following will make sure that firstMock was called before secondMock
//        inOrder.verify(firstMock).add("was called first");
//        inOrder.verify(secondMock).add("was called second");

    }

    @Test
    public void testMockito9(){
        List mockOne = mock(List.class);
        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void testMockito10(){
        List mockedList = mock(List.class);
        //using mocks
        mockedList.add("one");

        mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
//        verifyNoMoreInteractions(mockedList);
    }
}