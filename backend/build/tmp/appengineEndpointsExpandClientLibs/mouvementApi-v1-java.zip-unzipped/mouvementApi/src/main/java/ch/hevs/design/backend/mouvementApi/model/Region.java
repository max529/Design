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
 * on 2018-11-21 at 12:29:36 UTC 
 * Modify at your own risk.
 */

package ch.hevs.design.backend.mouvementApi.model;

/**
 * Model definition for Region.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the mouvementApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Region extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("_id") @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nom;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Pays pays;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Region setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNom() {
    return nom;
  }

  /**
   * @param nom nom or {@code null} for none
   */
  public Region setNom(java.lang.String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Pays getPays() {
    return pays;
  }

  /**
   * @param pays pays or {@code null} for none
   */
  public Region setPays(Pays pays) {
    this.pays = pays;
    return this;
  }

  @Override
  public Region set(String fieldName, Object value) {
    return (Region) super.set(fieldName, value);
  }

  @Override
  public Region clone() {
    return (Region) super.clone();
  }

}
