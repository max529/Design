/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2018-10-08 17:45:39 UTC)
 * on 2018-11-21 at 12:29:40 UTC 
 * Modify at your own risk.
 */

package ch.hevs.design.backend.vinApi.model;

/**
 * Model definition for Vin.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the vinApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Vin extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_id") @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer annee;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Cepage> cepage;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Couleur couleur;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String description;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String img;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double prix;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Provider provider;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer qte;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Region region;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Vin setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getAnnee() {
    return annee;
  }

  /**
   * @param annee annee or {@code null} for none
   */
  public Vin setAnnee(java.lang.Integer annee) {
    this.annee = annee;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Cepage> getCepage() {
    return cepage;
  }

  /**
   * @param cepage cepage or {@code null} for none
   */
  public Vin setCepage(java.util.List<Cepage> cepage) {
    this.cepage = cepage;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Couleur getCouleur() {
    return couleur;
  }

  /**
   * @param couleur couleur or {@code null} for none
   */
  public Vin setCouleur(Couleur couleur) {
    this.couleur = couleur;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * @param description description or {@code null} for none
   */
  public Vin setDescription(java.lang.String description) {
    this.description = description;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getImg() {
    return img;
  }

  /**
   * @param img img or {@code null} for none
   */
  public Vin setImg(java.lang.String img) {
    this.img = img;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * @param name name or {@code null} for none
   */
  public Vin setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getPrix() {
    return prix;
  }

  /**
   * @param prix prix or {@code null} for none
   */
  public Vin setPrix(java.lang.Double prix) {
    this.prix = prix;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Provider getProvider() {
    return provider;
  }

  /**
   * @param provider provider or {@code null} for none
   */
  public Vin setProvider(Provider provider) {
    this.provider = provider;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getQte() {
    return qte;
  }

  /**
   * @param qte qte or {@code null} for none
   */
  public Vin setQte(java.lang.Integer qte) {
    this.qte = qte;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Region getRegion() {
    return region;
  }

  /**
   * @param region region or {@code null} for none
   */
  public Vin setRegion(Region region) {
    this.region = region;
    return this;
  }

  @Override
  public Vin set(String fieldName, Object value) {
    return (Vin) super.set(fieldName, value);
  }

  @Override
  public Vin clone() {
    return (Vin) super.clone();
  }

}
