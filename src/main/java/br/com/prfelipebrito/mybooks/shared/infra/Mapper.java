package br.com.prfelipebrito.mybooks.shared.infra;

public interface Mapper<S, T> {
    T map(S source);
}
